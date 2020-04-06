# Dockerized SonarQube

SonarQube local in Docker ausführen

## Install Docker

## Pull SonarQube in Docker (Console)
```
docker pull sonarqube
```

## Start SonarQube in Docker

```
docker run -d --name sonarqube -p 9000:9000 -p 9092:9092 sonarqube
```

SonarQube Website ist über http://127.0.0.1:9000 erreichbar. 
Login/Passwort: admin/admin

## Sonar-scanner besorgen

- Sonar-Scanner via Docker: https://hub.docker.com/search?q=sonar-scanner&type=image

Alternativ:
- Sonar Scanner: https://docs.sonarqube.org/latest/analysis/scan/sonarscanner/
- An einem geeigneten Ort entpacken, z.B. ~/opt/sonar-scanner
- ggf. conf/sonar-scanner.properties ändern (global settings, e.g. for server url)
- in Ordner der zu prüfenden Source wechseln
- sonar-project.properties in sonar-project.local.properties umbennen
- Eintrag sonar.host.url=http://127.0.0.1:9000 ändern

Datei "sonar-project.properties" darf nicht existieren, da immer diese Datei verwendet wird, auch wenn man einen andere Config angibt. (siehe nächster Punkt)

## Sonar-scanner ausführen

```
./sonar-scanner -Dproject.settings=./sonar-project.local.properties -Dsonar.projectVersion=xyz
```

Resultat: Im lokalen SonarQube Website ist die Scan Auswertung verfügbar.

