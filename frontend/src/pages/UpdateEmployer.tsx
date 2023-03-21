import EmployerForm from '../components/Job/EmployerForm';

import { Box } from '@chakra-ui/react';
import { useContext } from 'react';
import { UserContext } from '../context/user';
import { IUserContext } from '../interfaces';
const UpdateEmployer = () => {
  const { user } = useContext(UserContext) as IUserContext;
  return (
    <Box minH="100vh">
      <EmployerForm
        formType="update"
        title="Update Employer"
        endpoint={`/employers/${user.employerId}`}
      />
    </Box>
  );
};

export default UpdateEmployer;
