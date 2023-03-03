import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import '@fontsource/raleway/400.css';
import '@fontsource/open-sans/700.css';
import { ChakraProvider } from '@chakra-ui/react';
import theme from './theme/theme';
import UserContextProvider from './context/user';

const root = ReactDOM.createRoot(document.getElementById('root') as HTMLElement);
root.render(
  <React.StrictMode>
    <ChakraProvider theme={theme}>
      <UserContextProvider>
        <App />
      </UserContextProvider>
    </ChakraProvider>
  </React.StrictMode>
);
