import { Box } from '@chakra-ui/react';
import { useContext } from 'react';
import { Link as RouterLink } from 'react-router-dom';
import { UserContext } from '../context/user';
import { IUserContext } from '../interfaces';

const Profile = () => {
  const { user } = useContext(UserContext) as IUserContext;
  return (
    <Box>
      <RouterLink to={`/profile/${user.profileId}/edit`}>Edit Profile</RouterLink>
    </Box>
  );
};

export default Profile;
