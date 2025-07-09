#!/bin/bash
docker run --rm -v "$PWD":/app -w /app node:latest npm install
docker run --rm -v "$PWD":/app -w /app node:latest npm test

