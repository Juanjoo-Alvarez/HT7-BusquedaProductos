/** @type {import('tailwindcss').Config} */
module.exports = {
    content: [
      "./src/**/*.{js,jsx,ts,tsx}",
    ],
    theme: {
      extend: {
        colors: {
          'frutiger-blue': '#0099ff',
          'frutiger-purple': '#9966ff',
          'frutiger-pink': '#ff66cc',
        },
        boxShadow: {
          'frutiger': '0 8px 32px rgba(31, 38, 135, 0.37)',
        },
        backdropBlur: {
          'frutiger': '8px',
        }
      },
    },
    plugins: [],
  }