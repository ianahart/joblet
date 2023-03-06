import { Box } from '@chakra-ui/react';
import { useCallback, useContext } from 'react';
import './App.css';
import Register from './pages/Auth/Register';
import Login from './pages/Auth/Login';
import Home from './pages/Home';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Footer from './components/Footer';
import Navbar from './components/Navbar';
import CompanyReviews from './pages/CompanyReviews';
import ForgotPassword from './pages/Auth/ForgotPassword';
import Joblet from './pages/Joblet';
import RequireGuest from './components/Guard/RequireGuest';
import RequireAuth from './components/Guard/RequireAuth';
import WithAxios from './helpers/WithAxios';
import { retreiveTokens } from './helpers/utils';
import { UserContext } from './context/user';
import { IUserContext } from './interfaces';
import { http } from './helpers/utils';
import { AxiosError } from 'axios';
import { useEffectOnce } from './hooks/UseEffectOnce';
import PasswordReset from './pages/Auth/PasswordReset';
function App() {
  const { setUser, user } = useContext(UserContext) as IUserContext;

  const storeUser = useCallback(async () => {
    try {
      const tokens = retreiveTokens();
      const response = await http.get('/users/sync', {
        headers: { Authorization: `Bearer ${tokens.token}` },
      });
      setUser(response.data);
    } catch (err: unknown | AxiosError) {
      if (err instanceof AxiosError && err.response) {
        console.log(err);
        return;
      }
    }
  }, [setUser]);

  useEffectOnce(() => {
    storeUser();
  });

  return (
    <Box as="main" className="App">
      <Router>
        <Navbar />
        <Box minH="100vh">
          <WithAxios>
            <Routes>
              <Route
                index
                element={
                  <RequireGuest>
                    <Home />
                  </RequireGuest>
                }
              />
              <Route
                path="register"
                element={
                  <RequireGuest>
                    <Register />
                  </RequireGuest>
                }
              />
              <Route
                path="login"
                element={
                  <RequireGuest>
                    <Login />
                  </RequireGuest>
                }
              />
              <Route
                path="company-reviews"
                element={
                  <RequireAuth>
                    <CompanyReviews />
                  </RequireAuth>
                }
              />
              <Route
                path="forgot-password"
                element={
                  <RequireGuest>
                    <ForgotPassword />
                  </RequireGuest>
                }
              />
              <Route
                path="reset-password"
                element={
                  <RequireGuest>
                    <PasswordReset />
                  </RequireGuest>
                }
              />

              <Route
                path="joblet"
                element={
                  <RequireAuth>
                    <Joblet />
                  </RequireAuth>
                }
              />
            </Routes>
          </WithAxios>
        </Box>
      </Router>
      <Footer />
    </Box>
  );
}

export default App;
