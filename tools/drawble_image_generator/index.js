const { ArgumentParser } = require('argparse')
const { dirname, join, basename } = require('path')
const { existsSync, mkdirSync, copyFileSync } = require('fs')
const sharp = require('sharp')

const DPI = {
  ldpi: 120, // x0.75
  mdpi: 160, // x1
  hdpi: 240, // x1.5
  xhdpi: 320, // x2
  xxhdpi: 480, // x3
  xxxhdpi: 640 // x4
}

const DIR_NAME = 'android_drawable'

function createArgumentParser() {
  const parser = new ArgumentParser({
    description: 'android用 画像変換ツール (drawable resource 生成)'
  })

  parser.add_argument('-i', '--input', { help: '入力画像', required: true })
  parser.add_argument('-o', '--output', {
    help: `出力先ディレクトリ (default: inputと同ディレクトリに"${DIR_NAME}"を生成)`
  })
  parser.add_argument('--base', {
    help: '基準とするdpi',
    choices: Object.keys(DPI),
    default: 'xxxhdpi'
  })

  return parser
}

async function main() {
  const args = createArgumentParser().parse_args()

  const input = args.input
  const output = args.output || join(dirname(args.input), DIR_NAME)
  const baseDpi = args.base

  try {
    if (!existsSync(input)) throw 'not exists input file'
    mkdirIfNot(output)

    await generate_drawable(input, output, baseDpi)

    console.log(`generate successed. dir: ${output}`)
  } catch (e) {
    console.error(e)
  }
}

async function generate_drawable(input, output, baseDpi) {
  // 変換用情報収集
  const name = basename(input)

  const inputSharp = sharp(input)
  const metadata = await inputSharp.metadata()

  // 変換
  for (const dpi in DPI) {
    const dpiDir = join(output, `drawable-${dpi}`)
    const dpiFile = join(dpiDir, name)

    mkdirIfNot(dpiDir)

    if (dpi == baseDpi) {
      // そのままコピー
      copyFileSync(input, dpiFile)
      console.log(`created for ${dpi} (copy)`)
    } else {
      const rate = DPI[dpi] / DPI[baseDpi]
      const _width = Math.round(metadata.width * rate)
      await inputSharp.resize(_width).toFile(dpiFile)

      console.log(`created for ${dpi} (x${rate})`)
    }
  }
}

function mkdirIfNot(dir) {
  if (existsSync(dir)) return false

  mkdirSync(dir, { recursive: true })
  return true
}

main()
