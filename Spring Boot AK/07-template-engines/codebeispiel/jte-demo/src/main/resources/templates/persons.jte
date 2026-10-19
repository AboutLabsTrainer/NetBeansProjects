@import java.util.List
@import de.ibb.demo.model.Person

@param List<Person> persons
@param String engine

<!DOCTYPE html>
<html>
<head>
    <title>Personen - JTE</title>
    <link rel="stylesheet" href="/css/style.css" />
</head>
<body>
    <div class="container">
        <h1>Personen-Verwaltung</h1>
        <p class="engine-badge">Template Engine: <strong>${engine}</strong></p>
        
        <div class="stats">
            <p>Gesamt: <strong>${persons.size()}</strong> Personen</p>
        </div>
        
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Telefon</th>
                    <th>Stadt</th>
                    <th>Position</th>
                    <th>Firma</th>
                </tr>
            </thead>
            <tbody>
                @for(Person person : persons)
                <tr>
                    <td>${person.getId()}</td>
                    <td>${person.getFullName()}</td>
                    <td>${person.getEmail()}</td>
                    <td>${person.getPhone()}</td>
                    <td>${person.getCity()}</td>
                    <td>${person.getJobTitle()}</td>
                    <td>${person.getCompany()}</td>
                </tr>
                @endfor
            </tbody>
        </table>
    </div>
</body>
</html>