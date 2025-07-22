# Staggo's Tweaked Adventure
Too many things bugging you from playing the Adventure Update? Well, this [Ornithe](https://ornithemc.net) mod fixes that!

## Supported Versions
- Beta 1.8.1
- Beta 1.9 Prerelease
- Beta 1.9 Prerelease 2
- Beta 1.9 Prerelease 5
- 1.0.0
- 1.1
- 1.2.5

## Built-in Features
- Fixed breaking times for all blocks
- Sprinting while flying
- Pigs drop extra porkchops for consistency with other mobs
- Fixed fancy tree height bug ([MC-11208](https://bugs.mojang.com/browse/MC/issues/MC-11208))
- Placeable snow layers on leaves
- (b1.8.1-1.1) Biome in debug menu
- (b1.9-pre) Restored continuous jumping
- (1.2.5) Accessible "Default 1.1" world type

## Configurable Features
These features are configurable in `.minecraft/config/tweaked-adventure.json`:
- Revert to Beta 1.7 passive mob spawning mechanics (default: enabled)
- Optional passive mob despawning (also disables mob breeding) (default: enabled)
- Hill biomes for varied height (default: enabled)
- Backport of 1.0.0 combat related changes (default: all enabled)
- Oak saplings in swamp biomes grow into swamp trees (default: enabled)
- Zombie Pigmen dropping cooked porkchops (default: enabled)
- Glass panes dropping themselves is optional (default: enabled before b1.9-pre2)
- Configurable experience bar with different label options ("none", "barOnly", "barAndSkillPoints", "barAndLevels") (default: "none" before b1.9-pre3, "barAndLevels" for b1.9-pre3+)
- Beta 1.7 ore spawning (default: enabled)
- Backported release 1.11+ hunger values (default: enabled)
- Backported release 1.3 experience curves and enchanting (max level reduced to 30) (default: enabled)
- Controllable biome height value presets ("b1.8", "r1.1", "r1.3", "hybrid") (default: "hybrid")
- Control generation of forests in the middle of plains (default: disabled before 1.1)
- Cheaper recipes (e.g. 6 slabs, 3 ladders) (default: enabled)
- Stackable signs and doors (synergises with cheaper recipes) (default: enabled)
- Control what blocks Endermen can pick up ("disabled", "restricted", "full") (default: "restricted")
- (b1.8.1) Remove oceans from edges of plains (default: enabled)
- (b1.8.1) Reduction of landmass size and distance (default: disabled)
- (b1.8.1-b1.9-pre5) Removed monsters interrupting sleep (default: enabled)
- (b1.9-pre+) Snowy taigas can generate in place of snowless taigas ("snowy"), be optionally disabled ("snowless") or generate as a separate variant that generates in ice plains regions ("snowyAndSnowless") (default: "snowyAndSnowless")
- (b1.9-pre+) Customizable chance of snowy taigas generating in ice plains (default: 4, same as 1.7; set to 7 for 1.3-1.6 gen)
- (b1.9-pre+) Revert swamp colors to lush green (default: disabled)
- (b1.9-pre+) Snow under leaves (default: disabled)
- (b1.9-pre+) Snow replaces tall grass, making ice plains look smoother (default: enabled)
- (b1.9-pre-1.0.0) Magma cubes drop magma cream (default: enabled)
- (1.2.5) Optional plank variants (default: enabled) 
