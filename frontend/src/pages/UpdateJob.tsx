import { Box } from '@chakra-ui/react';
import Form from '../components/Job/Form';
import { useParams } from 'react-router-dom';
import { useContext } from 'react';
import { UserContext } from '../context/user';
import { IUserContext } from '../interfaces';
const UpdateJob = () => {
  const params = useParams();
  const { user } = useContext(UserContext) as IUserContext;
  return (
    <Box minH="100vh">
      <Form
        employerId={user.employerId}
        type="update"
        title="Update Job"
        btnText="Update"
        endpoint={`/jobs/${params.id}`}
        jobId={params.id}
      />
    </Box>
  );
};

export default UpdateJob;
