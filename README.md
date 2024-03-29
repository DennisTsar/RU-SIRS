# RU-SIRS

A website & tool for viewing response from Rutgers SIRS class surveys. Built with 100% Kotlin.

The data comes from the [official SIRS website](https://sirs.rutgers.edu/index.php) and covers all
published surveys from the Spring 2014 through Spring 2022 semesters.

## Technologies + Tools

- [Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html), targeting JS and JVM
- [Kobweb](https://github.com/varabyte/kobweb), a framework on top
  of [Compose for Web](https://compose-web.ui.pages.jetbrains.team), for reactive web UI built entirely with Kotlin
- [GitHub Pages](https://pages.github.com/) for hosting, with [this](https://github.com/rafgraph/spa-github-pages)
  solution for single page apps
- [GitHub Actions](https://github.com/features/actions) for CI/CD
- [GoatCounter](https://www.goatcounter.com/) for privacy-friendly analytics (See live
  analytics [here](https://ru-sirs.goatcounter.com/))
- [Ktor](https://ktor.io/), [kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines),
  and [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization) for getting, storing, and parsing data
- [Font Awesome](https://fontawesome.com/) for icons
- [Balance text utility](https://github.com/adobe/balance-text) for a custom CSS property

## Project Structure

The project is divided into 3 modules:

- `common` - Contains shared code between the JVM and JS targets
- `data` - Contains the JVM target, which was used to scrape and parse the data from the SIRS website
- `site` - Contains the JS target, which is the website UI

See the individual READMEs for more details.

Note that the project also contains two automated GitHub Actions:

- `build_site.yml` - Builds the web project and deploys it to GitHub Pages whenever changes are pushed to the repository
- `get_latest_instructor.yml` (in the `common` submodule) - Uses the Rutgers API to get instructor listings, updating
  the json data files which are used by the website

### Acknowledgements

Design partially inspired by [this](https://kobweb.varabyte.com/) and [this](https://www.schedru.me/).

Thanks to DH, TG, and TF for their advice on this project.
