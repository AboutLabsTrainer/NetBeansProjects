#!/usr/bin/env bash
# Kompiliert Maven-Leaves inklusive Testcode (mvn test-compile). Tests laufen nicht, viele brauchen MySQL oder Payara.
#
#   scripts/build-leaves.sh all            alle Leaves außer Rheinwerk/ und Tmp/
#   scripts/build-leaves.sh changed <rev>  nur Leaves mit Änderungen seit <rev>, zum Beispiel origin/main
#
# Ein Leaf ist der nächste Ordner mit pom.xml über einer Datei. Liegt er in einem Multi-Modul-Projekt,
# baut das oberste Eltern-POM mit <modules> alle Kinder mit.
set -uo pipefail

cd "$(dirname "$0")/.."

excluded() {
    case "$1" in
        Rheinwerk/*|Tmp/*) return 0 ;;
        *) return 1 ;;
    esac
}

# Gibt zum Ordner das Projekt aus, das gebaut werden muss, oder nichts.
project_for() {
    local dir="$1" leaf=""
    while [ "$dir" != "." ] && [ -n "$dir" ]; do
        if [ -f "$dir/pom.xml" ]; then
            if [ -z "$leaf" ] || grep -q "<modules>" "$dir/pom.xml"; then
                leaf="$dir"
            fi
        fi
        dir=$(dirname "$dir")
    done
    [ -n "$leaf" ] && echo "$leaf"
}

case "${1:-}" in
    all)
        files=$(git ls-files -- '*pom.xml')
        ;;
    changed)
        rev="${2:?Revision fehlt, zum Beispiel origin/main}"
        files=$(git diff --name-only "$rev" HEAD)
        ;;
    *)
        echo "Aufruf: $0 all | changed <rev>" >&2
        exit 2
        ;;
esac

projects=$(
    while IFS= read -r f; do
        [ -n "$f" ] || continue
        excluded "$f" && continue
        project_for "$(dirname "$f")"
    done <<< "$files" | sort -u
)

if [ -z "$projects" ]; then
    echo "Keine Maven-Projekte betroffen."
    exit 0
fi

total=$(wc -l <<< "$projects")
failed=()
n=0
while IFS= read -r p; do
    n=$((n + 1))
    echo "::group::[$n/$total] $p"
    if (cd "$p" && mvn -B -q -ntp test-compile); then
        echo "::endgroup::"
    else
        echo "::endgroup::"
        echo "::error::Build fehlgeschlagen: $p"
        failed+=("$p")
    fi
done <<< "$projects"

echo "$((total - ${#failed[@]})) von $total Projekten gebaut."
if [ ${#failed[@]} -gt 0 ]; then
    printf 'Fehlgeschlagen: %s\n' "${failed[@]}"
    exit 1
fi
