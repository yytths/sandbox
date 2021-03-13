#!/bin/sh

PROJECT_NAME=python_docker
PYTHON_VERSION=3.9.2-slim

mkdir -p ./.devcontainer
mkdir -p ./src
touch ./requirements.txt

# docker設定
cat << EOS > ./.devcontainer/Dockerfile
FROM python:${PYTHON_VERSION}

RUN apt-get update
RUN apt-get install -y --no-install-recommends \
    apt-utils \
    gcc \
    build-essential
RUN pip install --no-cache-dir \
    autopep8 \
    flake8 \
    pytest
RUN apt-get autoremove -y
RUN apt-get clean -y
RUN rm -rf /var/lib/apt/lists/*
EOS

# リモート先で使うvscodeの拡張機能とかpythonの定義
cat << EOS > ./.devcontainer/devcontainer.json
{
    "name": "$PROJECT_NAME",
    "dockerFile": "Dockerfile",
    "settings": {
        "terminal.integrated.shell.linux": "/bin/bash",
        "python.pythonPath": "/usr/local/bin/python",
        "python.linting.pylintEnabled": false,
        "python.linting.flake8Enabled": true,
        "python.linting.flake8Args": [
            "--ignore=E402,E501"
        ],
        "python.formatting.provider": "autopep8",
        "python.formatting.autopep8Args": [
            "--ignore",
            "E402,E501",
            "--max-line-length",
            "150"
        ],
        "[python]": {
            "editor.formatOnSave": true
        }
    },
    "extensions": [
        "ms-python.python",
        "ms-pyright.pyright"
    ],
    "postCreateCommand": "pip install -r requirements.txt"
}
EOS

# pyrightの定義
cat << EOS > ./pyrightconfig.json
{
    "include": [
        "src"
    ],
    "reportTypeshedErrors": false,
    "reportMissingImports": true,
    "reportMissingTypeStubs": false,
    "pythonVersion": "3.7",
    "pythonPlatform": "Linux",
    "executionEnvironments": [
        {
            "root": "src"
        }
    ]
}
EOS

# sampleファイル
cat << EOS > ./src/sample.py
import sys
print(sys.version_info)
EOS
