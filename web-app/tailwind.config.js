/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        "primary": {
          100: "#947FF0",
          200: "#8D77FC",
          300: "#9861FF",
          400: "#570CED",
          500: "#4E0BD4",
          600: "#3D08D5",
          700: "#4609C2",
          800: "#3D089E",
          900: "#28056E",
        },
        "secondary": "#FF3D00",
        "light-white": "rgba(255,255,255,0.17)",
      },
    },
  },
  plugins: [],
}
