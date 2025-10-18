# TwitchInteractive

[![Java](https://img.shields.io/badge/Java-22-orange.svg)](https://openjdk.java.net/projects/jdk/22/)
[![Minecraft](https://img.shields.io/badge/Minecraft-1.21.8-green.svg)](https://www.minecraft.net/)
[![Spigot](https://img.shields.io/badge/Spigot-API-yellow.svg)](https://www.spigotmc.org/)

## Description

**TwitchInteractive** est un plugin Minecraft Bukkit/Spigot qui crée une intégration bidirectionnelle entre un serveur Minecraft et Twitch. Il permet aux spectateurs Twitch d'interagir avec le serveur Minecraft via le chat Twitch, et aux événements Twitch (follows, points de chaîne) de déclencher des actions dans le jeu.

## Fonctionnalités

- **Intégration Chat Twitch** : Les messages du chat Twitch sont relayés dans Minecraft
- **Commandes Twitch** : Exécution de commandes Minecraft depuis le chat Twitch
- **Événements Twitch** : Détection et réaction aux événements Twitch (follows, récompenses)
- **Système de commandes** : Interface de commandes dans Minecraft avec autocomplétion
- **Configuration flexible** : Configuration via le fichier `config.yml` du plugin

## Prérequis

- **Java** : JDK 22 ou supérieur
- **Minecraft Server** : Spigot/Paper 1.21.8 ou compatible
- **Compte Twitch Developer** : Pour obtenir les credentials API

## Installation

1. **Téléchargez le plugin** :
   ```bash
   git clone https://github.com/hydroxios/TwitchInteractive.git
   cd TwitchInteractive
   ./gradlew shadowJar
   ```

2. **Copiez le JAR** :
   Copiez le fichier `build/libs/TwitchInteractive-1.0.jar` dans le dossier `plugins/` de votre serveur Minecraft

3. **Redémarrez votre serveur**

## Configuration

Le plugin nécessite un fichier de configuration avec les informations Twitch suivantes :

```yaml
twitch:
  client_id: "your_twitch_client_id"
  client_secret: "your_twitch_client_secret"
  token: "your_twitch_oauth_token"
  channel: "your_twitch_channel_name"
```

### Obtention des credentials Twitch

1. **Créez une application Twitch** :
   - Allez sur [Twitch Developer Console](https://dev.twitch.tv/console)
   - Créez une nouvelle application
   - Notez le `Client ID` et `Client Secret`

2. **Générez un token OAuth** :
   - Utilisez un outil comme [twitchtokengenerator.com](https://twitchtokengenerator.com/)
   - Connectez-vous avec votre compte Twitch
   - Générez un token avec les scopes appropriés (chat:read, channel:read:redemptions)

## Utilisation

### Commandes dans Minecraft

- `/ti help` : Affiche l'aide des commandes disponibles
- `/ti <sous-commande>` : Exécute une sous-commande du plugin

### Commandes dans Twitch

Les spectateurs peuvent utiliser des commandes spéciales dans le chat Twitch qui seront exécutées dans Minecraft.

## Développement

### Structure du projet

```
src/main/java/fr/hydroxios/twitchInteractive/
├── TwitchInteractive.java      # Classe principale du plugin
├── command/                    # Gestionnaire de commandes
├── listener/                   # Écouteurs d'événements Minecraft
├── manager/                    # Gestionnaires
├── twitch/                     # Intégration Twitch
│   ├── Twitch.java            # Client Twitch principal
│   ├── manager/               # Gestionnaires Twitch
│   └── events/                # Écouteurs d'événements Twitch
└── utils/                      # Utilitaires
```

### Build

```bash
# Build le plugin
./gradlew build

# Démarre le serveur de test
./gradlew runServer
```

## Dépendances

- **Spigot API 1.21.8** : API Minecraft serveur
- **Twitch4J 1.16.0** : Bibliothèque Java pour l'API Twitch

## Support

Pour obtenir de l'aide ou signaler des bugs :
- Ouvrez une issue sur GitHub
- Contactez l'auteur sur Twitch/Discord

## Auteur

**Hydroxios** - Développeur Minecraft et Twitch

## Licence

Ce projet est sous licence MIT - voir le fichier LICENSE pour plus de détails.

## Remerciements

- [Twitch4J](https://github.com/twitch4j/twitch4j) pour l'excellente bibliothèque Twitch
- La communauté Spigot pour l'API Minecraft
- Tous les contributeurs et testeurs
