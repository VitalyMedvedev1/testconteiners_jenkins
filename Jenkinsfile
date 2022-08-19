pipeline {

    agent any

    options {
        timestamps()
        disableConcurrentBuilds()
        timeout(time: 1, unit: 'HOURS')
        buildDiscarder(logRotator(artifactDaysToKeepStr: '7', artifactNumToKeepStr: '10', daysToKeepStr: '7', numToKeepStr: '50'))
    }

    stages {

        // Извлекаем проект из Bitbucket
        stage('Checkout') {
            steps {
                echo 'Clean up current dir before git checkout'
                // Удаление всех файлов из рабочего каталога на сервере TestNode
                bat 'del /F /S /Q *.*'
                // Удаление всех папок из рабочего каталога на сервере TestNode
                bat 'for /d %%x in (.\\*) do @rd /s /q %%x'
                // Вывод echo в консоль Jenkins
                echo 'step Git Checkout'
                // Извлечение из системы контроля версий в рабочий каталог на сервере TestNode
                checkout scm
            }
        }

        // Собираем проект. Получаем на выходе пакетный файл AUDIT_Import_ALL.ispac
        stage('Build') {
            steps {
                echo 'BUILD STAGE'

                sh("chmod 755 ./gradlew testPrint")
            }
        }

        // Загружаем архив с проектом на удаленный сервер. Деплоим его на MS SQL Server
        //stage('Deploy') { }
    }
}