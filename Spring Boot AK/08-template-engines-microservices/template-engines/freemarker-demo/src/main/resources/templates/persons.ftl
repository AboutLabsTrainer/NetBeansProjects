<!DOCTYPE html>
<html>
<head>
    <title>Personen - FreeMarker</title>
    <link rel="stylesheet" href="/css/style.css" />
</head>
<body>
    <div class="container">
        <h1>Personen-Verwaltung</h1>
        <p class="engine-badge">Template Engine: <strong>${engine}</strong></p>
        
        <div class="stats">
            <p>Gesamt: <strong>${persons?size}</strong> Personen</p>
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
                <#list persons as person>
                <tr>
                    <td>${person.id}</td>
                    <td>${person.fullName}</td>
                    <td>${person.email}</td>
                    <td>${person.phone}</td>
                    <td>${person.city}</td>
                    <td>${person.jobTitle}</td>
                    <td>${person.company}</td>
                </tr>
                </#list>
            </tbody>
        </table>
    </div>
</body>
</html>
