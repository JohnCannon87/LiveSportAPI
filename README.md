# LiveSportAPI

Simple API to return live Sport info scraped from various sources

Example request: 
```
http://localhost:8080/api/matches?daysInFuture=14&excludeChannels=MUTV,OneFootball,TBC,Premier%20Sports%201,Premier%20Sports%202&excludeComps=.*Under-21.*
```

Example respose: 
```
[
  {
    "competition": "UEFA Europa Conference League Final",
    "homeTeam": {
      "name": "Real Betis"
    },
    "awayTeam": {
      "name": "Chelsea"
    },
    "kickoff": "2025-05-28T20:00:00",
    "channels": [
      {
        "name": "TNT Sports 1"
      },
      {
        "name": "TNT Sports Ultimate"
      },
      {
        "name": "discovery+"
      }
    ]
  },
  {
    "competition": "UEFA Champions League Final",
    "homeTeam": {
      "name": "PSG"
    },
    "awayTeam": {
      "name": "Inter Milan"
    },
    "kickoff": "2025-05-31T20:00:00",
    "channels": [
      {
        "name": "TNT Sports 1"
      },
      {
        "name": "TNT Sports Ultimate"
      },
      {
        "name": "discovery+"
      }
    ]
  },
  {
    "competition": "FIFA World Cup 2026 Qualifier",
    "homeTeam": {
      "name": "Wales"
    },
    "awayTeam": {
      "name": "Liechtenstein"
    },
    "kickoff": "2025-06-06T19:45:00",
    "channels": [
      {
        "name": "BBC One Wales"
      },
      {
        "name": "BBC Three"
      },
      {
        "name": "S4C"
      },
      {
        "name": "BBC iPlayer"
      },
      {
        "name": "S4C Online"
      },
      {
        "name": "BBC Sport Website"
      }
    ]
  },
  {
    "competition": "International Friendly",
    "homeTeam": {
      "name": "Scotland"
    },
    "awayTeam": {
      "name": "Iceland"
    },
    "kickoff": "2025-06-06T19:45:00",
    "channels": [
      {
        "name": "BBC One Scotland"
      },
      {
        "name": "BBC iPlayer"
      },
      {
        "name": "BBC Sport Website"
      }
    ]
  },
  {
    "competition": "FIFA World Cup 2026 Qualifier",
    "homeTeam": {
      "name": "Andorra"
    },
    "awayTeam": {
      "name": "England"
    },
    "kickoff": "2025-06-07T17:00:00",
    "channels": [
      {
        "name": "ITV1"
      },
      {
        "name": "STV"
      },
      {
        "name": "ITVX"
      },
      {
        "name": "STV Player"
      }
    ]
  },
  {
    "competition": "UEFA Nations League Final",
    "homeTeam": {
      "name": "TBC"
    },
    "awayTeam": {
      "name": "TBC"
    },
    "kickoff": "2025-06-08T20:00:00",
    "channels": [
      {
        "name": "ITV TBC"
      }
    ]
  },
  {
    "competition": "International Friendly",
    "homeTeam": {
      "name": "Liechtenstein"
    },
    "awayTeam": {
      "name": "Scotland"
    },
    "kickoff": "2025-06-09T17:00:00",
    "channels": [
      {
        "name": "BBC One Scotland"
      },
      {
        "name": "BBC iPlayer"
      },
      {
        "name": "BBC Sport Website"
      }
    ]
  },
  {
    "competition": "FIFA World Cup 2026 Qualifier",
    "homeTeam": {
      "name": "Belgium"
    },
    "awayTeam": {
      "name": "Wales"
    },
    "kickoff": "2025-06-09T19:45:00",
    "channels": [
      {
        "name": "BBC One Wales"
      },
      {
        "name": "S4C"
      },
      {
        "name": "BBC iPlayer"
      },
      {
        "name": "S4C Online"
      },
      {
        "name": "BBC Sport Website"
      }
    ]
  },
  {
    "competition": "International Friendly",
    "homeTeam": {
      "name": "England"
    },
    "awayTeam": {
      "name": "Senegal"
    },
    "kickoff": "2025-06-10T19:45:00",
    "channels": [
      {
        "name": "ITV1"
      },
      {
        "name": "STV"
      },
      {
        "name": "ITVX"
      },
      {
        "name": "STV Player"
      }
    ]
  },
  {
    "competition": "International Friendly",
    "homeTeam": {
      "name": "Northern Ireland"
    },
    "awayTeam": {
      "name": "Iceland"
    },
    "kickoff": "2025-06-10T19:45:00",
    "channels": [
      {
        "name": "BBC One NI"
      },
      {
        "name": "BBC iPlayer"
      },
      {
        "name": "BBC Sport Website"
      }
    ]
  }
]
```