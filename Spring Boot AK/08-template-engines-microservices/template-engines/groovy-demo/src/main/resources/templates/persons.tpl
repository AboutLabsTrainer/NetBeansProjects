yieldUnescaped '<!DOCTYPE html>'
html {
    head {
        title('Personen - Groovy Templates')
        link(rel: 'stylesheet', href: '/css/style.css')
    }
    body {
        div(class: 'container') {
            h1('Personen-Verwaltung')
            p(class: 'engine-badge') {
                yield 'Template Engine: '
                strong(engine)
            }
            
            div(class: 'stats') {
                p {
                    yield 'Gesamt: '
                    strong(persons.size())
                    yield ' Personen'
                }
            }
            
            table {
                thead {
                    tr {
                        th('ID')
                        th('Name')
                        th('Email')
                        th('Telefon')
                        th('Stadt')
                        th('Position')
                        th('Firma')
                    }
                }
                tbody {
                    persons.each { person ->
                        tr {
                            td(person.id)
                            td(person.fullName)
                            td(person.email)
                            td(person.phone)
                            td(person.city)
                            td(person.jobTitle)
                            td(person.company)
                        }
                    }
                }
            }
        }
    }
}