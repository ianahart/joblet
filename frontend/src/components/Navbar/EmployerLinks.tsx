import { useContext } from 'react';
import { UserContext } from '../../context/user';
import { IUserContext } from '../../interfaces';
import ProfileNavigationLink from './ProfileNavigationLink';
import { HiOutlineDocumentDuplicate } from 'react-icons/hi';
import { AiOutlineUser } from 'react-icons/ai';

interface IEmployerlinks {
  setShowProfile: (open: boolean) => void;
}

const EmployerLinks = ({ setShowProfile }: IEmployerlinks) => {
  const { user } = useContext(UserContext) as IUserContext;

  return (
    <>
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
    </>
  );
};

export default EmployerLinks;
