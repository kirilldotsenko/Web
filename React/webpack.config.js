const path = require("path");
const merge = require("webpack-merge");

module.exports = e => {

  let o = merge(
    {},
    {
      entry: "./calc.js",
      output: {
        path: path.join(__dirname, "dist"),
        filename: `calc.js`
      },
      resolve: {
        modules: [
          path.resolve(__dirname, "./node_modules"),
        ],
        extensions: [".js", ".jsx"]
      },
      module: {
        rules: [
          {
            test: /\.jsx?$/,
            exclude: /node_modules/,
            use: [
              {
                loader: "babel-loader"
              }
            ]
          }
        ]
      },
      plugins: [
      ]
    }
  );

  return o;
};