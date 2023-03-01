import { extendTheme } from '@chakra-ui/react';
export const theme = extendTheme({
  fonts: {
    heading: `'Open Sans', sans-serif`,
    body: `'Raleway', sans-serif`,
  },

  components: {},
  colors: {
    border: {
      primary: '#e4e4e6',
    },
    black: {
      primary: '#181717',
      secondary: '#200F21',
    },
    text: {
      primary: '#8a8f9d',
      secondary: '#565757',
      tertiary: '#403d40',
    },
    light: {
      primary: '#e4e3e3',
    },
    blue: {
      primary: '#22577a',
    },
    green: {
      primary: '#38a3a5',
      secondary: '#57cc99',
      tertiary: '80ed99',
    },
    cover: {
      primary: 'rgba(54, 54, 54, 0.8)',
    },
  },
});
export default theme;
