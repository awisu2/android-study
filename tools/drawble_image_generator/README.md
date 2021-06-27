# drawable_image_generator

android の drawable resource 生成簡単スクリプトです

```bash
yarn install

# リサイズ
node .\index.js -i {your_ image_path}

# help
node .\index.js --help

# 出力先の変更、対象dpiの変更
node .\index.js -i {your_ image_path} -o {output_dir} --base mdpi
```

- 上記のコマンドで、画像と同じディレクトリに android_drawable というディレクトリが生成されます
  - android studio で、 ResourceManager を開きドラッグすることで取り込むことが可能です
- セットされた画像はデフォルトでは最大解像度(xxxhdpi)を想定してリサイズをしています(そのほうがきれいなので)
  - 詳細は以下のリンクより
- 別の解像度用を基準にしたリサイズが必要な場合は `--base hdpi` と希望する dpi を指定してください
  - 指定する dpi によってはリサイズ時に拡大することになるので画像が荒くなる可能性があります

## links

- [各種のピクセル密度をサポートする  |  Android デベロッパー  |  Android Developers](https://developer.android.com/training/multiscreen/screendensities?hl=ja)
