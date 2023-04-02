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
import Profile from './pages/Profile';
import EditProfile from './pages/EditProfile';
import DocumentView from './components/Profile/DocumentView';
import CreateEmployer from './pages/CreateEmployer';
import CreateJob from './pages/CreateJob';
import EmployerJobs from './pages/EmployerJobs';
import RequireEmployerAuth from './components/Guard/RequireEmployerAuth';
import ViewEmployerJob from './pages/ViewEmployerJob';
import UpdateEmployer from './pages/UpdateEmployer';
import UpdateJob from './pages/UpdateJob';
import ViewJob from './pages/ViewJob';
import SavedJobs from './pages/SavedJobs';
import EmployerInbox from './pages/EmployerInbox';
import EmployerApplication from './pages/EmployerApplication';
import WriteReview from './pages/WriteReview';
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
              <Route path="company-reviews" element={<CompanyReviews />} />
              <Route
                path="write-review"
                element={
                  <RequireAuth>
                    <WriteReview />
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

              <Route
                path="profile/:id"
                element={
                  <RequireAuth>
                    <Profile />
                  </RequireAuth>
                }
              />

              <Route
                path="profile/:id/edit"
                element={
                  <RequireAuth>
                    <EditProfile />
                  </RequireAuth>
                }
              />
              <Route
                path="document-view"
                element={
                  <RequireAuth>
                    <DocumentView />
                  </RequireAuth>
                }
              />
              <Route
                path="create-employer"
                element={
                  <RequireAuth>
                    <CreateEmployer />
                  </RequireAuth>
                }
              />
              <Route
                path="create-job"
                element={
                  <RequireAuth>
                    <CreateJob />
                  </RequireAuth>
                }
              />
              <Route
                path="employer-jobs"
                element={
                  <RequireEmployerAuth>
                    <EmployerJobs />
                  </RequireEmployerAuth>
                }
              />

              <Route
                path="employer-jobs/:id"
                element={
                  <RequireEmployerAuth>
                    <ViewEmployerJob />
                  </RequireEmployerAuth>
                }
              />
              <Route
                path="jobs/:id"
                element={
                  <RequireAuth>
                    <ViewJob />
                  </RequireAuth>
                }
              />
              <Route
                path="saved-jobs"
                element={
                  <RequireAuth>
                    <SavedJobs />
                  </RequireAuth>
                }
              />
              <Route
                path="update-employer/:id"
                element={
                  <RequireEmployerAuth>
                    <UpdateEmployer />
                  </RequireEmployerAuth>
                }
              />
              <Route
                path="update-job/:id"
                element={
                  <RequireEmployerAuth>
                    <UpdateJob />
                  </RequireEmployerAuth>
                }
              />
              <Route
                path="employers/inbox"
                element={
                  <RequireEmployerAuth>
                    <EmployerInbox />
                  </RequireEmployerAuth>
                }
              />
              <Route
                path="applications/:id"
                element={
                  <RequireEmployerAuth>
                    <EmployerApplication />
                  </RequireEmployerAuth>
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
