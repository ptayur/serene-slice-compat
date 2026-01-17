<div align="center">
  <img src="https://raw.githubusercontent.com/ptayur/serene-slice-compat/forge_1.20.1/src/main/resources/assets/sereneslicecompat/icon.png" alt="Logo" width="128">
  <h1 align="center">Serene & Slice Compat</h1>
  <a href="https://www.curseforge.com/minecraft/mc-mods/serene-slice-compat/files"><img src="https://cf.way2muchnoise.eu/versions/1420698.svg" alt="Supported Versions" align="center"></a>
  <a href="https://www.curseforge.com/minecraft/mc-mods/serene-slice-compat"><img src="https://cf.way2muchnoise.eu/full_1420698_downloads.svg" alt="CurseForge" align="center"></a>
  <a href="https://modrinth.com/mod/serene-slice-compat"><img src="https://img.shields.io/modrinth/dt/OdyyFUwv?logo=modrinth&label=&labelColor=%2300AF5C&color=2D2D2D&logoColor=1C1C1C" alt="Modrinth" align="center"></a>
  <br>
  <a>Fixes irrigation logic and restores sprinkler sound</a>
</div>

## Info

A small compatibility mod that restores proper sprinkler behavior when
[Create: Slice & Dice](https://github.com/PssbleTrngle/SliceAndDice) is used together with
[Serene Seasons](https://github.com/Glitchfiend/SereneSeasons).

<p align="center">
  <img src="https://raw.githubusercontent.com/ptayur/serene-slice-compat/forge_1.20.1/screenshots/serene_slice_compat_showcase.gif" alt="Showcase" width="720">
</p>

<details>
<summary><strong>Incompatibility reasons</strong></summary>

- Both mods inject mixins into Minecraft’s `isRainingAt()` method.
  Each mixin targets `@At("HEAD")`, uses the same priority, and is marked as `cancellable`.

  When multiple cancellable `HEAD` injections with equal priority exist, Mixin applies them sequentially in the order
  their mixin configurations are loaded. The first injected callback executes first and can cancel the method, 
  preventing later injections from running.

  In this case, Serene Seasons’ mixin configuration is resolved earlier, so its injection is applied first and cancels 
  the method. As a result, Create: Slice & Dice’s injection never executes.


- Because Create: Slice & Dice relies on Minecraft’s vanilla weather system to trigger rain-related sound effects, 
  overriding the vanilla weather logic in Serene Seasons prevents the sprinkler from playing its intended rain sounds.

</details>

<details>
<summary><strong>How this mod fixes the issues</strong></summary>

- Instead of injecting directly into Minecraft’s `isRainingAt()` method, this compatibility mod integrates the
  Create: Slice & Dice sprinkler check into Serene Seasons’ `isRainingAtHook()`, ensuring both mods’ logic is evaluated
  consistently.


- To restore the missing audio, the mod also plays the appropriate rain sound directly at the sprinkler’s
  position, bypassing reliance on the vanilla weather system.
</details>

