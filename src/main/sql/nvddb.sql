-- Nazel Video Downloader
-------------------------------------
--
-- File generated with SQLiteStudio v3.1.1 on Fri Jun 9 23:31:08 2017
-- Text encoding used: UTF-8
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: playlistItems
CREATE TABLE playlistItems (
    id                    INTEGER PRIMARY KEY
                                  NOT NULL,
    url                   TEXT    NOT NULL,
    location              TEXT    NOT NULL,
    title                 TEXT    NOT NULL,
    customName            TEXT    NOT NULL,
    speedLimit            INTEGER NOT NULL,
    isAddedToQueue            BOOLEAN NOT NULL,
    isVideo               BOOLEAN NOT NULL,
    format                TEXT    NOT NULL,
    videoQuality          INTEGER NOT NULL,
    audioQuality          INTEGER NOT NULL,
    subtitleLanguage      TEXT    NOT NULL,
    needEmbeddedSubtitle      BOOLEAN NOT NULL,
    needAutoGeneratedSubtitle BOOLEAN NOT NULL,
    isPlaylist            BOOLEAN NOT NULL,
    playlistStartIndex            INTEGER,
    playlistEndIndex              INTEGER,
    playlistItems                 TEXT,
    needAllPlaylistItems              BOOLEAN NOT NULL,
    done                  DECIMAL,
    size                  DECIMAL,
    sizeUnit              TEXT
);


COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
