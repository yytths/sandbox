## 導入ライブラリ

|ライブラリ名|説明|
|---|---|
|autopep8|PEP8（コーディングスタイル）に準拠するように自動修正|
|flake8|文法チェック|

## apt updateでエラー

dockerデーモンが動いているホストの時刻がずれているのが原因。dockerをrestartする。
それでもだめならPC再起動する。(スリープとの相性が悪いらしい)

- [参考](https://qiita.com/nobuoka/items/1bb8cbb5af7be5259547)
