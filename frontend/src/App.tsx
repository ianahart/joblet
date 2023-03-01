import { Box } from '@chakra-ui/react';
import './App.css';
import Register from './pages/Auth/Register';
import Login from './pages/Auth/Login';
import Home from './pages/Home';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Footer from './components/Footer';
import Navbar from './components/Navbar';
import CompanyReviews from './pages/CompanyReviews';

function App() {
  return (
    <Box as="main" className="App">
      <Router>
        <Navbar />
        <Box minH="100vh">
          <Routes>
            <Route index element={<Home />} />
            <Route path="register" element={<Register />} />
            <Route path="login" element={<Login />} />
            <Route path="company-reviews" element={<CompanyReviews />} />
          </Routes>
        </Box>
      </Router>
      <Footer />
    </Box>
  );
}

export default App;
