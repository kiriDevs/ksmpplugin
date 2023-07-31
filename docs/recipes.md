# Custom Recipes

> [!NOTE]
> This file documents all possible configuration options. If you are just
> looking for default values, check the default `config.yml` file at
> `/src/main/resources/config.yml`.
> 
> **The default values are set after what is used on kiriSMP4!**

While it is specifically made for the kiriSMP, `ksmpplugin`'s custom recipes are
partly user-configurable to allow other users to twek their experience with it. 
This overview describes all properties of recipes you can change under the
`recipes` key in the plugin's `config.yaml`.

> [!IMPORTANT]
> Unless otherwise noted, any YAML key expects a boolean value (`true` or
> `false`), with `true` being assumed the default. In these cases, `true` will
> mean "enabled" and `false` will mean "disabled". If an item has an existing
> recipe, `true` will disable it in favor of the custom recipe.

> [!WARNING]
> Note that disabling a recipe (`false`) is interpreted as "don't touch
> anything", **not** "disallow crafting this". This means that any pre-existing
> recipes (vanilla mc, mods, other plugins, etc.) will remain in effect.

## Crafting Recipes (`crafting`)

### Chain Armor (`chainArmor`)

This allows chainmail armor to be crafted. The recipe shapes are the same as
any armor, though the material can be configured:

Value       | Description      | Default?
----------- | ---------------- | --------
`"chains"`  | Use chains       | :white_check_mark:
`"nuggets"` | Use iron nuggets
`false`     | Don't register any recipe

### Chains (`chain`)

For crafting chains, there are two configurable properties:

YAML Key | Type    | Default Value | Description
-------- | ------- | ------------- | -----------
`cheap`  | boolean | `false`       | Whether to use the cheap recipe
`yield`  | integer | `2`           | The amount of chains per craft

The cheap recipe replaces the iron ingot found in the vanilla recipe with a
nugget, resulting in a vertical line of iron nuggets in the crafting grid.

### Bundle (`bundle`)

While Mojang has created and published an official recipe in an
experimental-feature datapack, that recipe is not in the vanilla game yet.
The kiriSMP3 had a slightly different recipe, and because of this conflict the
kiriSMP4 version of `ksmpplugin` does not enforce either of the recipes.
Instead, both options (and a few more) are made possible by allowing for user
configuration. For examples on what config achieves what recipe, see below.

#### Configuration

There are two configurable properties for this recipe:
- `chest` (determines if a chest is required in the center) [default: `false`]
- `shell` (determines the required shell material)
  - `"leather"` (only allows leather as shell material)
  - `"rabbit"` (only allows rabbit hide as shell material)
  - `"either"` (allows crafting with either leather or rabbit hide) [default]
  - `"mixed"` (allows crafting even when mixing leather and rabbit hide)

You can also set `bundle: false` to not register any new recipes.

#### Examples

<table>
  <tr> <!-- START ROW name -->
    <td>kiriSMP3</td>
    <td>Mojang</td>
    <td>kiriSMP4</td>
    <td>Disabled</td>
  </tr> <!-- END ROW name -->

  <tr> <!-- START ROW recipe -->
  <td>

  ```
  S | L | S
  - + - + -
  L | C | L
  - + - + -
  L | L | L

  [S]tring
  [L]eather
  [C]hest
  ```

  </td>
  <td>

  ```
  S | R | S
  - + - + -
  R |   | R
  - + - + -
  R | R | R

  [S]tring
  [R]abbit Hide
  
  ```

  </td>
  <td>

  ```
  S | M | S
  - + - + -
  M |   | M
  - + - + -
  M | M | M

  [S]tring
  [M]aterial (Leather or Rabbit Hide)

  ```

  </td>
  <td></td>
  </tr> <!-- END ROW recipe -->

  <tr> <!-- START ROW config -->
  <td>

  ```yaml
  bundle:
    chest: true
    shell: leather
  ```

  </td>
  <td>
  
  ```yaml
  bundle:
    chest: false
    shell: rabbit
  ```

  </td>
  <td>

  ```yaml
  bundle:
    chest: false
    shell: either
  ```

  </td>
  <td>

  ```yaml
  bundle: false
  ```

  </td>
  </tr> <!-- END ROW config -->
</table>

### Saddle (`saddle`)

```
L | L | L
- + - + -
S |   | S
- + - + -
H |   | H

[L]eather
[S]tring
Tripwire [H]ook
```

### Cheaper (Soul) Lantern (`cheapLantern`)

```
  | N |
- + - + -
N | T | N
- + - + -
  | N |

Iron [N]ugget
(Soul) [T]orch
```

### Bell (`bell`)

```
R | S | R
- + - + -
G | G | G
- + - + -
G | I | G

[R]ock (Stone)
[S]tick
[G]old Ingot
[I]ron Ingot
```

## Stonecutting Recipes (`stonecutting`)

This section is designated for stonecutting recipes, used in the stonecutter.

### Wooden Items (`wood`)

This section determines which wooden item groups can be created in the
stonecutter. Enable a group with `true`, disable with `false`.

> Yes, I know, it is technically called a **stone**cutter, it shouldn't cut
> wood. But, if you look at it, it is literally just a table saw. Why would it
> *not* be able to?
> \- Kiri

This is the full list of supported groups:

Block Group | YAML Key | Default Value
----------- | -------- | -------------
Slabs       | `slabs`  | `true`
Stairs      | `stairs` | `true`
