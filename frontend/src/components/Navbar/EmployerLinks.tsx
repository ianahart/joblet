import { Box } from '@chakra-ui/react';
import { useContext } from 'react';
import { UserContext } from '../../context/user';
import { IUserContext } from '../../interfaces';
import ProfileNavigationLink from './ProfileNavigationLink';
import { HiOutlineDocumentDuplicate } from 'react-icons/hi';
import { AiOutlineMail, AiOutlineUser } from 'react-icons/ai';

interface IEmployerlinks {
  setShowProfile: (open: boolean) => void;
}

const EmployerLinks = ({ setShowProfile }: IEmployerlinks) => {
  const { user } = useContext(UserContext) as IUserContext;

  return (
    <Box ml="1rem">
      {user.employerId !== 0 && user.employerId !== null && (
        <ProfileNavigationLink
          to={`employer-jobs`}
          text="Employer Jobs"
          setShowProfile={setShowProfile}
          icon={<HiOutlineDocumentDuplicate />}
        />
      )}

      {user.employerId !== 0 && user.employerId !== null && (
        <ProfileNavigationLink
          to={`update-employer/${user.employerId}`}
          text="Update Employer"
          setShowProfile={setShowProfile}
          icon={<AiOutlineUser />}
        />
      )}

      {user.employerId !== 0 && user.employerId !== null && (
        <ProfileNavigationLink
          to={`employers/inbox`}
          text="Inbox"
          setShowProfile={setShowProfile}
          icon={<AiOutlineMail />}
        />
      )}
    </Box>
  );
};

export default EmployerLinks;
