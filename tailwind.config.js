
const colors = require('tailwindcss/colors')

module.exports = {
  //purge: [
  //  './src/**/*.html',
  //],
  //darkMode: false, // or 'media' or 'class'
  theme: {
    extend: {
      colors: {
          blue: colors.blue,
          yellow: colors.yellow,
          lime: colors.lime,
      },
      screens: {
        'xs': '425px',

        'sm': '640px',
        // => @media (min-width: 640px) { ... }

        'md': '768px',
        // => @media (min-width: 768px) { ... }

        'lg': '1024px',
        // => @media (min-width: 1024px) { ... }

        'xl': '1280px',
        // => @media (min-width: 1280px) { ... }

        '2xl': '1536px',
        // => @media (min-width: 1536px) { ... }

        '4xl': '2560px',
      }
    },
  },
  variants: {
    extend: {},
  },
  plugins: [
    require('@tailwindcss/forms'),
  ],
}
