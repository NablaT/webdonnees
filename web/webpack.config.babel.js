'use strict';

const webpack = require('webpack');
const cleanPlugin = require('clean-webpack-plugin');
const ngAnnotatePlugin = require('ng-annotate-webpack-plugin');

let config = {
  context: `${__dirname}/app`,
  entry: './index.js',
  output: {
    path: `${__dirname}/app`,
    filename: 'bundle.js'
  },
  resolve: {
    alias: {
      'npm': `${__dirname}/node_modules`
    }
  },
  module: {
    loaders: [
      {
        test: /\.css$/,
        loader: 'style!css'
      },
      {
          test: /\.js?$/,
          exclude: /node_modules/,
          loader: 'babel!jshint'
      }
    ]
  },
  plugins: [
    new webpack.HotModuleReplacementPlugin(),
    // new cleanPlugin(['dist']),
    // new ngAnnotatePlugin({
    //   add: true
    // })
  ]
}

module.exports = config;
