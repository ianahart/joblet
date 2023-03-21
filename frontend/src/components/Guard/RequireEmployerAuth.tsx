import { useContext } from 'react';
import { useLocation, Navigate } from 'react-router-dom';
import { UserContext } from '../../context/user';
import { retreiveTokens } from '../../helpers/utils';
import { IUserContext } from '../../interfaces';
interface Props {
  children: JSX.Element;
}

const RequireEmployerAuth: React.FC<Props> = ({ children }): JSX.Element => {
  const { user } = useContext(UserContext) as IUserContext;
  const location = useLocation();

  if (retreiveTokens()?.token && user.employerId !== null) {
    return children;
  } else {
    return <Navigate to="/joblet" replace state={{ path: location.pathname }} />;
  }
};

export default RequireEmployerAuth;
