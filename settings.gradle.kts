pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
       // jcenter()

    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        //jcenter()
       // gradlePluginPortal()
        //maven (url ="https://repository.liferay.com/nexus/content/repositories/punlic/")
        //maven (url ="https://jcenter.bintray.com")
    }
}

rootProject.name = "X Sefety"
include(":app")
 