import { Box, Flex, Button, Text } from '@chakra-ui/react';
import Header from '../components/Header';
import { useNavigate } from 'react-router-dom';
import { AiOutlineMail } from 'react-icons/ai';
import { AxiosError } from 'axios';
import { useContext, useEffect, useState } from 'react';

import { http } from '../helpers/utils';
import { UserContext } from '../context/user';
import { IApplication, IRetrieveApplicationResponse, IUserContext } from '../interfaces';

const EmployerInbox = () => {
  const navigate = useNavigate();
  const { user } = useContext(UserContext) as IUserContext;
  const [page, setPage] = useState(0);
  const [direction, setDirection] = useState('next');
  const [totalPages, setTotalPages] = useState(0);
  const [applications, setApplications] = useState<IApplication[]>([]);

  useEffect(() => {
    const retrieveApplications = async () => {
      try {
        const response = await http.get<IRetrieveApplicationResponse>(
          `/applications/?employerId=${user.employerId}&page=-1&size=3&direction=next`
        );
        console.log(response);
        setApplications(response.data.applications);
        setPage(response.data.page);
        setTotalPages(response.data.totalPages);
      } catch (err: unknown | AxiosError) {
        if (err instanceof AxiosError && err.response) {
          return;
        }
      }
    };
    if (user.employerId !== 0) {
      retrieveApplications();
    }
  }, [user.employerId]);

  const paginateApplications = async (dir: string) => {
    try {
      if (page === totalPages && dir !== 'prev') {
        return;
      }
      setDirection(dir);
      const response = await http.get<IRetrieveApplicationResponse>(
        `/applications/?employerId=${user.employerId}&page=${page}&size=3&direction=${dir}`
      );
      setApplications(response.data.applications);
      setPage(response.data.page);
      setTotalPages(response.data.totalPages);
    } catch (err: unknown | AxiosError) {
      if (err instanceof AxiosError && err.response) {
        return;
      }
    }
  };

  const navigateToApplication = (application: IApplication) => {
    navigate(`/applications/${application.id}`, { state: { application } });
  };

  return (
    <Box minH="100vh">
      <Box mt="5rem">
        <Header
          icon={<AiOutlineMail />}
          title="Employer Inbox"
          text="Job applications sent to you."
        />
      </Box>

      <Box width={['95%', '95%', '650px']} mx="auto">
        <table>
          <tbody>
            <tr>
              <th>Company</th>
              <th>Position</th>
              <th>Date</th>
              <th>Details</th>
            </tr>

            {applications.map((application) => {
              return (
                <tr key={application.id}>
                  <td>{application.jobCompany}</td>
                  <td>{application.jobPosition}</td>
                  <td>{application.createdAt?.toString().split('T')[0]}</td>
                  <td
                    style={{ cursor: 'pointer', color: '#38a3a5' }}
                    onClick={() => navigateToApplication(application)}
                  >
                    View application
                  </td>
                </tr>
              );
            })}
          </tbody>
        </table>
      </Box>

      <Flex justifyContent="center" mt="3rem" alignItems="center">
        {page + 1 > 1 && (
          <Button onClick={() => paginateApplications('prev')}>Prev</Button>
        )}
        <Box>
          <Text mx="1rem">
            {page + 1} of {totalPages}
          </Text>
        </Box>
        {page + 1 !== totalPages && (
          <Button onClick={() => paginateApplications('next')}>Next</Button>
        )}
      </Flex>
    </Box>
  );
};

export default EmployerInbox;
