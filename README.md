# SimpClient
This was my 2021 AprilFools joke. It is not intended to be a fully functional client what so ever. A lot of what you saw in the video was faked.
<br>
<br>
Quite a number of people on the Discord wanted this to be released, so I have put it up on GitHub. Please see the building instructions below.
<br>
<br>
 _**If you are not familier with MCP, creating Minecraft Clients, or Git: DO NOT attempt to build this! You will get very frustrated very quickly. There will be NO SUPPORT for building this client.**_
 <br>
 <br>
  There are lots of bugs, weird quirks, and undocumented code. This was slapped together in a few days because I procrastinated April Fools again.

## Building
1) Decompile a fresh MCP 918 (Minecraft 1.8.8)
2) Download the optifine source code `OptiFine 1.8.8 HD U H8`, and copy the `optifine`folder and the `shadersmod` folder into your MCP src folder. DO NOT copy any of the `net.minecraft` files.
3) Copy `jars`, `lib`, `src` from this repo into your MCP ROOT folder.
4) Apply the [patch file](patches/net.minecraft.patch) in this repo to the `net.minecraft` files.
5) Refresh your project to make sure your IDE is happy
6) Start simping from in game

## Bugs & Quirks
 - Currently, only Belle is finished in terms of the sky textures. Poki and Neekolul have a textureless sky, but everything else works 

  - Resource packs don't work fully anymore due to the crude way I implemented customizing the game at the beginning with the Pick Your Simp screen.

  - The SimpTracker was hard coded to render the logo on every player nametag. There is no server that keeps track of who is actually using the client

  - SimpJuice and any cosmetics have not been implemented. The whole SimpJuice idea was a crude way of making a sex joke. 

  - The anti damage isn't apart of the client, that was just the regeneration potion effect.

  - Logging in into your accounts is totally fake, and does not actually send your account information cycle out. The onlyFans billing cycle mod always returns the same date, it does not actually work.

  - The web browser is almost fully functional, but needs a bit of try catch statements so it doesn't fully crash your game. The microphone doesn't work, and twitch video player does not work.

  - Shitty code that isn't well documented

## Resources / Snippits Used
 - [viserys Command System](https://discord.com/channels/594335572173258752/632743056113926154/815238483610173450)
 - [CatLover's  Render Image Next To Player List](https://discord.com/channels/594335572173258752/632743056113926154/823817471839567902)
 - [CatLover's  Render Image Next To Player Name](https://discord.com/channels/594335572173258752/632743056113926154/823821440032702535)
 - [My tutorial series](https://www.youtube.com/playlist?list=PLxbv-Ej1VQMQS9M2qnmEQtp-qL3xcA4ua)

## Final Notes
  Please don't use this code just to skid. I released this to show how I pulled off some of the tricks seen in the video. 
  <br>
  <br>
  If you have an intrest in coding Minecraft Clients and haven't already, [join the Support server](https://discord.gg/M3PAyyy)!