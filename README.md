# kiriSMP Plugin

[![Maven Build](https://github.com/kiriDevs/ksmpplugin/actions/workflows/maven_build.yml/badge.svg)](https://github.com/kiriDevs/ksmpplugin/actions/workflows/maven_build.yml)
[![CodeQL](https://github.com/kiriDevs/ksmpplugin/actions/workflows/codeql.yml/badge.svg)](https://github.com/kiriDevs/ksmpplugin/actions/workflows/codeql.yml)

> The official plugin for turning a regular Minecraft server into the kiriSMP4!

(Built on the Paper API)

## Features

### For Players

- Various custom crafting recipes (e.g. chain armor, bundles)
- Variants of existing crafting recipes (e.g. cheaper chains, lanterns)
- Custom stonecutting recipes for wooden slabs and stairs

### For Admins

- High configurability via `config.yml`
  - Individual recipes can be disabled
  - Some recipes offer multiple variants and options to choose from

### Detailed Documentation

- For a list of all possible recipes (including their configuration options),
  see [`/docs/recipes.md`](/docs/recipes.md).

## Usage

> [!INFO]
> Since `ksmpplugin` is built against the [PaperMC](https://papermc.io) API,
> your server should run on Paper (or a compatible fork). It will not run on
> "bare" Spigot, or anything else that's not (based on) Paper.

1. **Acquire a build (.jar) of the plugin** using one of the following methods: <br>
   - **Download a release version from GitHub Releases**
     1. Go to [the "Releases" section](https://github.com/kiriDevs/ksmpplugin/releases)
     2. Search the release you want to install
     3. Download `ksmpplugin-{version}.jar` from the "Assets" section

   - **Build your own JAR from source** <br>
     1. Make sure you have the required prerequisites installed:
        - a Java Development Kit (JDK), version 18 or higher
        - [Maven](https://maven.apache.org/)
     2. Clone this GitHub repository (use `git clone` or GitHub's "Download ZIP")
     3. At the root of your new local copy, run `mvn package`
    
     You can now find your JAR under `./target/ksmpplugin-{version}.jar`.

2. **Add your JAR file to your server's `plugins` folder** <br>
   If your server is on a remote machine, use your usual file transfer method,
   such as FTP / SFTP, SSH or even a hoster's web interface.

3. **Restart your Minecraft server**
   Once loaded, the plugin will create a folder called `kiriSMP` in your
   server's `plugins` folder.

4. **Configurate the plugin** *(optional)*
   If you want to change the default behaviour, find the plugin's `config.yml`
   in the folder created in step 3. In that file, make changes as required
   (for help, [see `/docs/recipes.md`](/docs/recipes.md)).
   Make sure to restart your server again to load your new settings.
