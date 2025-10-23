#!/bin/bash

BACKUP_DIR="backup"

# Create backup directory if it doesn't exist
mkdir -p "$BACKUP_DIR"

echo -n "Enter the full path of the file to back up: "
read FILE_PATH

if [[ -f "$FILE_PATH" ]]; then
  # Extract filename and append timestamp
  FILENAME=$(basename "$FILE_PATH")
  TIMESTAMP=$(date +%Y%m%d_%H%M%S)
  BACKUP_FILE="${BACKUP_DIR}/${FILENAME}_${TIMESTAMP}"

  cp "$FILE_PATH" "$BACKUP_FILE"
  echo "Backup created at $BACKUP_FILE"
else
  echo "File does not exist."
fi
