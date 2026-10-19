document.addEventListener("DOMContentLoaded", async () => {
    setupLogButtons();
    setupCLI();
    setupFilters();
    await initializePage();
});

async function initializePage() {
    const response = await fetch("/api/user/current");
    const user = await response.json();

    updateAuthenticationUi(user);

    if (user.authenticated && user.admin) {
        await loadLogs();
        await loadCliHist();
        connectWebSocket();
    }
}

function updateAuthenticationUi(user) {
    // passt gui an benutzer-status an...
    // nur angemeldete benutzer duerfen Logs einfuegen, nur admins sie ansehen...
    // Todo: Das muss natuerlich spaeter auf die db-verbindungen, etc angepasst werden.
    const panelA = document.getElementById("panelA");
    const panelB = document.getElementById("panelB");

    if (!panelA || !panelB) return;

    const panelANotice = document.getElementById("panelANotice");
    const panelBNotice = document.getElementById("panelBNotice");

    if (user.authenticated) {
        panelANotice?.remove();
    } else { // alle buttons deaktiviert rendern
        panelA.querySelectorAll("button")
            .forEach(button => button.disabled = true);
    }

    if (user.admin) {
        panelBNotice?.remove();
    } else {
        panelB.querySelectorAll("select, input")
            .forEach(element => element.disabled = true);

        const logs_element = document.getElementById("logs");
        if (logs_element!=null) { // when not logged in 'logs' is hidden => test, so we don't spam errors to the console
            logs_element.textContent =
                user.authenticated
                    ? "Administrator access is required to view logs."
                    : "Login as an administrator to view logs.";
        }
    }
}


/// fuer die Log-Dummy buttons die Logik anbinden...
function setupLogButtons() {
    document.querySelectorAll("button[data-level]")
        .forEach(button => {
            button.addEventListener("click", async () => {
                const level = button.dataset.level;

                const response = await fetch("/api/logs/create", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify({
                        level: level,
                        message: `Created ${level} log entry`
                    })
                });

                if (response.ok) {
                    const message = document.getElementById("message");

                    if (message) {
                        message.textContent =
                            `${level} log entry created`;
                    }
                } else if (response.status === 401) { // falls die authentifizierung abgelaufen ist
                    window.location.href = "/login";
                } else {
                    alert("You are not allowed to create logs.");
                }
            });
        });
}

function setupCLI() {
    document.getElementById("console:cli:input:send")
        .addEventListener("click", async () => {
            const command = document.getElementById("console:cli:input").value;

            const response = await fetch("/api/cli/call", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: command.trim()
                // body: JSON.stringify({
                //     command: command.trim(),
                //     success: `SUCCESS`,
                //     result: ``
                // })
            });

            if (response.ok) {
                const message = document.getElementById("cli:message");

                if (message) {
                    message.textContent = `command sent ${command}`;
                }
            } else if (response.status === 401) { // falls die authentifizierung abgelaufen ist
                window.location.href = "/login";
            } else {
                alert("You are not allowed to use the console...");
            }
        });
}

async function loadLogs() {
    const response = await fetch("/api/logs/recent");

    if (!response.ok) return;

    logs = await response.json();
    renderLogs();
}

async function loadCliHist() {
    const response = await fetch("/api/cli/list");

    if (!response.ok) return;

    cliHists = await response.json();
    renderCLI();
}

// old version didn't properly connect => removed. check in dl_smallB to see old state
function connectWebSocket() {
    const protocol =
        window.location.protocol === "https:"
            ? "wss"
            : "ws";

    const client = new StompJs.Client({
        brokerURL: `${protocol}://${window.location.host}/ws`,

        reconnectDelay: 5000,

        debug: message => console.debug("[STOMP]", message)
    });

    client.onConnect = () => {
        console.log("WebSocket connected");

        client.subscribe("/topic/logs", message => {
            logs.push(JSON.parse(message.body));
            renderLogs();
        });

        client.subscribe("/topic/cli", message => {
            cliHists.push(JSON.parse(message.body));
            renderCLI();
        });

    };

    client.onStompError = frame => {
        console.error("STOMP error:", frame);
    };

    client.onWebSocketError = error => {
        console.error("WebSocket error:", error);
    };

    client.activate();
    stompClient = client;
}

function setupFilters() {
    document.getElementById("levelFilter")
        ?.addEventListener("change", renderLogs);

    document.getElementById("fromFilter")
        ?.addEventListener("change", renderLogs);

    document.getElementById("toFilter")
        ?.addEventListener("change", renderLogs);

    document.getElementById("textFilter")
        ?.addEventListener("input", renderLogs);

    // ----------------------------------------------
    // ----------------------------------------------
    document.getElementById("logs:Filter:Time:from")
        ?.addEventListener("change", renderLogs);

    document.getElementById("logs:Filter:Time:to")
        ?.addEventListener("change", renderLogs);

    document.getElementById("logs:Filter:Level:from")
        ?.addEventListener("change", renderLogs);

    document.getElementById("logs:Filter:Level:to")
        ?.addEventListener("change", renderLogs);

    // ----------------------------------------------
    document.getElementById("logs:Filter:Text:include")
        ?.addEventListener("input", renderLogs);

    document.getElementById("logs:Filter:Text:includeCase")
        ?.addEventListener("change", renderLogs);

    document.getElementById("logs:Filter:Text:exclude")
        ?.addEventListener("input", renderLogs);

    document.getElementById("logs:Filter:Text:excludeCase")
        ?.addEventListener("change", renderLogs);
}

let logs = [];
let cliHists = [];
let stompClient = null;

function renderLogs() {
    // renderLogsSimple(); // no longer relevant...
    renderLogsTable();
}
function renderLogsSimple() {
    const container = document.getElementById("logs");

    if (!container) {
        return;
    }

    // const selectedLevel =
    //     document.getElementById("levelFilter")?.value || "";

    // Get raw values
    const minimumLevel = document.getElementById("levelFilter")?.value || "INFO";
    const fromValue = document.getElementById("fromFilter")?.value || "";
    const toValue = document.getElementById("toFilter")?.value || "";
    const searchText = document.getElementById("textFilter")?.value || "";

    // Extract refined values
    const minimumPriority = levelPriority(minimumLevel);
    const from = fromValue ? new Date(fromValue).getTime() : Number.MIN_SAFE_INTEGER;
    const to = toValue ? new Date(toValue).getTime() : Number.MAX_SAFE_INTEGER;

    // Clear container and render filtered logs
    container.innerHTML = "";

    logs
        // .filter(log =>
        //     !selectedLevel || log.level === selectedLevel
        // )
        .filter(log =>
            levelPriority(log.level) >= minimumPriority
        )
        .filter(log => {
            const timestamp = new Date(log.timestamp).getTime();
            return timestamp >= from && timestamp <= to;
        })
        .filter(log =>
            !searchText ||
            log.message.toLowerCase().includes(searchText.toLowerCase())
        )
        .slice()
        .reverse()
        .forEach(log => {
            // todo: refactor to allow pinning, hiding type and timestamp, maybe gridlayout.
            const element = document.createElement("div");

            element.className = `log ${log.level}`;
            element.textContent =
                `[${log.timestamp}] ${log.level}: ${log.message}`;

            container.appendChild(element);
        });
}

function filteredLogsStream() {
    // Get raw values
    const timeFrom = document.getElementById("logs:Filter:Time:from")?.value || "";
    const timeTo   = document.getElementById("logs:Filter:Time:to")?.value || "";
    const levelFrom = document.getElementById("logs:Filter:Level:from")?.value || "";
    const levelTo   = document.getElementById("logs:Filter:Level:to")?.value || "";
    const textInclude = document.getElementById("logs:Filter:Text:include")?.value || "";
    const textExclude = document.getElementById("logs:Filter:Text:exclude")?.value || "";
    const textIncludeCase = document.getElementById("logs:Filter:Text:includeCase")?.value || "Case Sensitive";
    const textExcludeCase = document.getElementById("logs:Filter:Text:excludeCase")?.value || "Case Sensitive";

    // Extract refined values
    const levelMin = levelFrom ? levelPriority(levelFrom) : Number.MIN_SAFE_INTEGER;
    const levelMax = levelTo ? levelPriority(levelTo) : Number.MAX_SAFE_INTEGER;
    const from = timeFrom ? new Date(timeFrom).getTime() : Number.MIN_SAFE_INTEGER;
    const to = timeTo ? new Date(timeTo).getTime() : Number.MAX_SAFE_INTEGER;
    const caseSensIncl= textIncludeCase === "Case Sensitive";
    const caseSensExcl= textExcludeCase === "Case Sensitive";
    const incl= caseSensIncl ? textInclude : textInclude.toLowerCase();
    const excl= caseSensExcl ? textExclude : textExclude.toLowerCase();

    // Clear container and render filtered logs
    return logs
        .filter(log => { // LEVEL
            const logLevel = levelPriority(log.level);
            return logLevel >= levelMin && logLevel <= levelMax;
        })
        .filter(log => { // TIMESTAMP
            const timestamp = new Date(log.timestamp).getTime();
            return timestamp >= from && timestamp <= to;
        })
        .filter(log => {// TEXT SEARCH INCLUDE
            const msg = caseSensIncl ? log.message.toString() : log.message.toLowerCase();
            return !textInclude || msg.includes(incl);
        })
        .filter(log => {// TEXT SEARCH EXCLUDE
            const msg = caseSensExcl ? log.message.toString() : log.message.toLowerCase();
            return !textExclude || !msg.includes(excl);
        })
        .slice();
}

function renderLogsTable() {
    const container = document.getElementById("logs:Table:body");

    if (!container) {
        return;
    }

    container.innerHTML = "";

    filteredLogsStream()
        .reverse() // so we get the most recent first ...
        .forEach(log => {
            // todo: refactor to allow pinning, hiding type and timestamp, maybe gridlayout.

            const row = document.createElement("tr");
            const row_time = document.createElement("td");
            const row_level = document.createElement("td");
            const row_message = document.createElement("td");

            row_time.className = `log ${log.level}`;
            row_level.className = `log ${log.level}`;
            row_message.className = `log ${log.level}`;

            row_time.textContent = `[${log.timestamp}]`;
            row_level.textContent = `${log.level}:`;
            row_message.textContent = `${log.message}`;

            row.appendChild(row_time);
            row.appendChild(row_level);
            row.appendChild(row_message);

            container.appendChild(row);
        });
}


function filteredCLIStream() {
    // Clear container and render filtered logs
    return cliHists
        .slice();
}

function renderCLI() {
    const container = document.getElementById("console:Table:body");

    if (!container) {
        return;
    }

    container.innerHTML = "";

    const cli_num = document.getElementById("CLI:Number");
    cli_num.textContent = cliHists.length.toString();
    filteredCLIStream()
        .reverse() // so we get the most recent first ...
        .forEach(hist => {
            // todo: refactor to allow pinning, hiding type and timestamp, maybe gridlayout.

            // to toggle styles bases on overall success/failure/error (command becomes green, orange, red)
            let subclass = (hist.commandState=="SUCCESS")?`INFO`:`ERROR`;
            subclass = (hist.commandState=="FAILURE")?`WARN`:subclass;

            // We add the history entries as "details" (so they're collapsible) with buttons to copy command and result
            const details = document.createElement('details');
            const summary = document.createElement('summary');

            const summarySpan = document.createElement('span');
            const detailsSpan = document.createElement('span');

            summarySpan.textContent = hist.command;
            summarySpan.classList.add(subclass);
            details.classList.add('single-line-summary'); // styling to make summary collapse together with content...
            detailsSpan.textContent = hist.result;


            // let summary_Btn_Holder = makeCopyButton(summary, hist.command, "span");
            const summaryCopy = mkCopyButton(summarySpan);
            summary.appendChild(summaryCopy);
            summary.appendChild(summarySpan);
            // summaryCopy.style.marginRight = "10px"; // Kleiner Abstand zum Text
            // summary.innerHTML = summarySpan.outerHTML+":"+summaryCopy.outerHTML;

            details.appendChild(summary);

            // let details_Btn_Holder = makeCopyButton(details, hist.result, "span");
            const detailsCopy = mkCopyButton(detailsSpan);
            detailsCopy.style.marginLeft = "18px"; // buttons have margin=4px. with this, both copy buttons align
            details.appendChild(detailsCopy);
            details.appendChild(detailsSpan);


            // --- ADD TO THE TABLE ---
            // column for the actual history
            const row_message = document.createElement("td");
            row_message.className = `log NOTE`; // make the content blue
            row_message.appendChild(details);

            // later we want to add a timestamp. Maybe a remove-button too
            // const row_time = document.createElement("td");
            // row_time.className = subclass;
            // row_time.textContent = `[${hist.timestamp}]`; // we don't have a timestamp yet

            const row = document.createElement("tr");
            // row.appendChild(row_time);
            row.appendChild(row_message);

            container.appendChild(row);
        });
}

function mkCopyButton(textSource) {
    const btnText = "copy";
    const btnTextSuccess = "copied!";

    const btn = document.createElement('button');

    // --- BUTTON IM SUMMARY (Immer sichtbar) ---
    btn.textContent = btnText;
    // btn.style.marginRight = "10px"; // Kleiner Abstand zum Text
    btn.addEventListener('click', function(event) {
        // WICHTIG: Verhindert, dass das Details-Element auf-/zuklappt!
        event.stopPropagation();
        // console.log("Summary-Button geklickt für: ");
    });
    btn.addEventListener("click", async () => {
        // copy to clipboard
        if (navigator.clipboard) {
            await navigator.clipboard.writeText(textSource.textContent);
        }
        // change text of button after copying
        btn.innerText = btnTextSuccess;
        // change text back to normal after ### ms
        setTimeout(() => btn.innerText = btnText, 1500);
    })
    return btn;
}
function makeCopyButton(base, textContent="", textholder="div") {
    const btnText = "copy";
    const btnTextSuccess = "copied!";

    const btn = document.createElement('button');
    const holder = document.createElement(textholder);

    holder.textContent = textContent;

    // --- BUTTON IM SUMMARY (Immer sichtbar) ---
    btn.textContent = btnText;
    btn.style.marginLeft = "10px"; // Kleiner Abstand zum Text
    btn.addEventListener('click', function(event) {
        // WICHTIG: Verhindert, dass das Details-Element auf-/zuklappt!
        event.stopPropagation();
        // console.log("Summary-Button geklickt für: ");
    });
    btn.addEventListener("click", async () => {
        // copy to clipboard
        if (navigator.clipboard) {
            await navigator.clipboard.writeText(holder.innerText);
        }
        // change text of button after copying
        btn.innerText = btnTextSuccess;
        // change text back to normal after ### ms
        setTimeout(() => btn.innerText = btnText, 1500);
    })
    base.appendChild(holder);
    base.appendChild(btn);
    return [btn, holder];
}

function levelPriority(level) {
    switch (level) {
        case "ERROR":
            return 4;
        case "WARN":
            return 3;
        case "NOTE":
            return 2;
        case "INFO":
            return 1;
        default:
            return 1; // or should we return 0?
    }
}


try { // Just try setting up the create-user logic. If we're not logged in, it is irrelevant.
    var btnAddUser = document.getElementById('add-user-btn')
    btnAddUser.addEventListener("click", async _ => {
        try {
            const u_name = document.getElementById("userName").value;
            const p_word = document.getElementById("userPassword").value;
            const role = document.getElementById("userRoles").value;

            const response = await fetch('/api/user/create', {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    username: u_name,
                    password: p_word,
                    roles: role.split(',')
                })
            });
            if (response.status === 500) {
                console.error('Error: Unable to create user. Unsupp password-encoder?');
                const btnTxt = btnAddUser.innerText;
                btnAddUser.innerText = "ERR: PW";
                setTimeout(() => btnAddUser.innerText = btnTxt, 1500);
            }
            if (response.status === 406) {
                console.error('Error: Unsupported password-encoder!');
                const btnTxt = btnAddUser.innerText;
                btnAddUser.innerText = "ERR: Bad Password";
                setTimeout(() => btnAddUser.innerText = btnTxt, 1500);
            }
            if (response.status === 409) {
                console.error('Error: User already exists!');
                const btnTxt = btnAddUser.innerText;
                btnAddUser.innerText = "ERR: Duplicate user!";
                setTimeout(() => btnAddUser.innerText = btnTxt, 1500);
            }
            console.log('Created user!', response);
        } catch (err) {
            console.error(`Error: ${err}`);
        }
    });
} catch (err) {}
