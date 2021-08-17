const path = require("path");
const webpack = require("webpack");

const HtmlWebPackPlugin = require('html-webpack-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');
const VueLoaderPlugin = require('vue-loader/lib/plugin');

module.exports = {
    mode: 'development',
    entry: './src/main.js',
    output: {
        path: path.resolve(__dirname, 'www'),
        filename: (chunkData) => {
            return chunkData.chunk.name === 'service-worker'
                ? '[name].js'
                : '[name].[chunkhash:8].js';
        },
        publicPath: '',
    },
    devtool: "inline-source-map",
    devServer: {
        contentBase: './www',
        overlay: true // 오류내용을 화면에 보여주게 함.
    },
    resolve: {
        alias: {
            // vue$: 'vue/dist/vue.esm.js'
            // 'vue$': 'vue/dist/vue.common.js',
            'vue$': 'vue/dist/vue.esm.js'
        },
        extensions: ['.ts', '.js', '.json', '.jsx', '.css'],
        modules: ['node_modules'],
    },
    optimization: {
        minimize: false,
        splitChunks: {},
        concatenateModules: true, // false with ace
    },
    module: {
        rules: [
            {
                test: /\.ts?$/,
                loader: "ts-loader",
                options: { appendTsSuffixTo: [/\.vue$/] }
            },
            {
                test: /\.vue?$/,
                loader: "vue-loader",
                options: { esModule: true }
            },
            {
                test: /\.css$/i,
                use: [
                    process.env.NODE_ENV !== 'production'
                        ? 'vue-style-loader'
                        : 'style-loader',
                    'css-loader'
                ]
            },
        ]
    },
    plugins: [
        new webpack.LoaderOptionsPlugin({ minimize: true }),
        new webpack.EnvironmentPlugin({ NODE_ENV: 'development', DEBUG: false }),
        new CleanWebpackPlugin({ cleanAfterEveryBuildPatterns: ['./www'] }),
        new HtmlWebPackPlugin({ template: './src/index.html', filename: './www/index.html' }),
        new CopyWebpackPlugin({
            patterns: [
                // { from: './www/style.css', to: '.' },
                // { from: './fonts/**/*.{otf,woff,woff2,json,ttf}', to: '.' },
                // { from: './externals/kakao.min.js', to: './js' },
            ],
        }),
        new VueLoaderPlugin(),
        new webpack.HotModuleReplacementPlugin()
    ],
};