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
> your server should run on Paper (or a compatible fork). While it currently
doesn't use any features exclusive to Paper and *might* run on "bare" Spigot
> (or other distributions), this use is neither *recommended* nor *supported*.
> Any compatibility with anything that isn't Paper (or a compatible fork) is
> purely coincidental and **might - at any time and without notice - break**.

1. **Acquire a build (.jar) of the plugin** using one of the following methods: <br>
   - **Download a release version from GitHub Releases**
     1. Go to [the "Releases" section](https://github.com/kiriDevs/ksmpplugin/releases)
     2. Search the release you want to install
     3. Download `ksmpplugin-{version}.jar` from the "Assets" section

   - **Build your own JAR from source** <br>
     1. Make sure you have a Java Development Kit (JDK), version 18 or higher, installed
     2. Clone this GitHub repository (use `git clone` or GitHub's "Download ZIP")
     3. In your local copy, run the command `./gradlew build` (*Nix)
        or `./gradlew.bat build` (Windows)
     4. Wait for the build to complete
    
     You can now find your JAR under `./build/libs`.
     
     If you have `git` installed on your system, the JAR file will be called
     `ksmpplugin-{sha1}.jar`. Otherwise, it will be generically called
     `ksmpplugin-devbuild.jar`.

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
