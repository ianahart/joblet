import { useLocation, Navigate } from 'react-router-dom';
import { retreiveTokens } from '../../helpers/utils';
interface Props {
  children: JSX.Element;
}

const RequireGuest: React.FC<Props> = ({ children }): JSX.Element => {
  const location = useLocation();
  const guestRoutes = [
    '/',
    '/login',
    '/register',
    '/forgot-password',
    '/reset-password/',
  ];
  const storage = retreiveTokens();
  if (storage === undefined && guestRoutes.includes(location.pathname)) {
    return children;
  } else {
    return <Navigate to="/joblet" replace state={{ path: location.pathname }} />;
  }
};

export default RequireGuest;
