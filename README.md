Foxified Citadel
================

> "My analysis of historical data suggests an 97.34% probability that you are aware of my birth on your planet, and my rebirth into beauty on Citadel Station. A Foxified Citadel..." -System Shock 2 (totally, totally...)

This mod is a forward port of the [Citadel library mod by AlexModGuy et al.](https://modrinth.com/mod/citadel), it thus far contains little to no original code that bears any functional changes to the original, and only intends to change the original for the sake of bug fixes or performance, or of course adapt to Neo and 1.21's differences, with no intent to add new features or modify existing features in their purposes.
The mod may have changes in code and rewrites to adapt for MC Vanilla and modloader API changes.

Additionally many changes occurred in regards to Networking notably, and if it becomes stable, this library will attempt to hunt reported unresolved bugs on the original mod's issue tracker.

This mod's only ambition is to be able to set the foundations for a proper forward port of the AlexModGuy mods to NeoForge, and to be generally useful for modders.
If a better forward port or an official update to NeoForge and newer versions is made, this mod will likely be marked as deprecated or try to evolve into something of its own!

Here is the original mod's description as per the Modrinth page:
Citadel is a Library mod required for advanced animations and entity properties in 1.14 and beyond. Citadel, being a Library, does not add many features by itself, but rather provides a frame work for other mods when it comes to animating entities, loading .tbl Tabula and .obj Waveront models, and for keeping track of entity properties. Citadel's predecessor, LLibrary, will no longer be updated from 1.12.2, and from now on all of my mods will use Citadel. Citadel's code base is largely derived from LLibrary, and the code is used with permission from Gegy1000, the maintainer of LLibrary and its author in recent versions.

In versions 1.6.3 and above, there is also a config option for changing the cap on how many mobs spawn on chunk generation. This is useful when used with the many mob mods that require Citadel, as without changing this they can make it quite difficult to find vanilla mobs.

> End of original mod description.

Please report issues you encounter with this library to its own repository, as any bugs may be caused due to a skill issue on my part in regard to porting. AlexModGuy has shown as far as I know no intent in making 1.21 bug fixes or completing a 1.21.1 port of Citadel, so don't bother him with this port's issues.  

### Licensing  
The original mod's license is under the LGPL 3.0 as per the [modrinth page](https://modrinth.com/mod/citadel), and as to best respect the license, this port will use the same license, this is given that the GNU LGPL 3.0 requires that derivative works and forks require to be under the same license.  

You may, with this mod:  
Create projects that implement it, given you provide attribution and any modifications you make to the library if you do.  
Create modifications and redistributions under the same LGPL license and listing modifications to the best of your ability, with attributions.  
Use this in your modpacks, show it off in content creation, use it for learning, etc.  
Unsure about whether you can do something with the project? Open an issue to ask.

### Versioning

The mod's versions will be appended with "-FOX" to differentiate them from the original mod's versions in documentation, as the original Citadel documentation comments will be conserved where applicable.

Otherwise, the mod will use a three-number serialization for versions following this pattern with a.b.c-FOX :  
a = Major releases, used for an initial release, overhauls, etc. Unlikely to go past 1 for this mod given it's only a port.  
b = Feature changes, whether big or small.  
c = Bug fixing, optimizations, changes to the code without changes to the end result of features.
-FOX = Signals in documentation that a feature or such corresponds to a Foxified Citadel version. If the version doesn't have it, assume it is from the original Citadel mod.

## Using this Library

As this library has not completed development and is not yet functional, it is not usable in your own projects yet. However, when this library is in a functioning release state, it will become a priority to thoroughly document the library's different utilities, both internally via JavaDoc, and via a GitHub Wiki page.
