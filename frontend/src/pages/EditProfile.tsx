import { Box, Button, Flex, Input, Text } from '@chakra-ui/react';
import { useContext, useState } from 'react';
import FormDropdown from '../components/Form/FormDropdown';
import StandardFormInput from '../components/Form/StandardFormInput';
import { countries, editProfileState, states, userState } from '../data';
import { IEditProfileForm, IProfile, IUserContext } from '../interfaces';
import { AiFillEdit, AiOutlineCheckCircle } from 'react-icons/ai';
import Header from '../components/Header';
import { UserContext } from '../context/user';
import { AxiosError } from 'axios';
import { http } from '../helpers/utils';
import { useEffectOnce } from '../hooks/UseEffectOnce';
import { useParams } from 'react-router-dom';
import InputContainer from '../components/Form/InputContainer';

const EditProfile = () => {
  const { id: profileId } = useParams();
  const [form, setForm] = useState(editProfileState);
  const [updated, setUpdated] = useState(false);
  const { user } = useContext(UserContext) as IUserContext;

  const updateField = (name: string, value: string, attribute: string) => {
    setForm((prevState) => ({
      ...prevState,
      [name]: {
        ...prevState[name as keyof IEditProfileForm],
        [attribute]: value === null ? '' : value,
      },
    }));
  };

  const checkForErrors = () => {
    let errors = false;
    for (const [_, field] of Object.entries(form)) {
      if (field.error.length) {
        errors = true;
      }
    }
    return errors;
  };

  const handleOnSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (checkForErrors()) return;
    editProfile();
  };

  const applyErrors = <T,>(data: T) => {
    for (let prop in data) {
      updateField(prop, data[prop] as any, 'error');
    }
  };

  const getProfile = async () => {
    try {
      const response = await http.get(`/profiles/${profileId}`);
      preFillForm(response.data.profile);
    } catch (err: unknown | AxiosError) {
      if (err instanceof AxiosError && err.response) {
        return;
      }
    }
  };

  const preFillForm = (data: IProfile) => {
    const exclude = ['id', 'fileName'];
    for (let prop in data) {
      if (exclude.includes(prop)) {
        continue;
      }
      updateField(prop, data[prop as keyof IProfile] as string, 'value');
    }
  };

  const editProfile = async () => {
    try {
      setUpdated(false);
      await http.patch(`/profiles/${user.profileId}`, {
        email: form.email.value,
        phoneNumber: form.phoneNumber.value,
        city: form.city.value,
        state: form.state.value,
        country: form.country.value,
        resume: form.resume.value,
      });
      setUpdated(true);
    } catch (err: unknown | AxiosError) {
      if (err instanceof AxiosError && err.response) {
        applyErrors(err.response.data);
      }
    }
  };

  useEffectOnce(() => {
    getProfile();
  });

  return (
    <Box bg="light.secondary" minH="100vh">
      <Box
        minH="500px"
        display="flex"
        flexDir="column"
        justifyContent="center"
        mx="auto"
        width={['95%', '95%', '599px']}
      >
        <form onSubmit={handleOnSubmit}>
          <Flex justifyContent="center" width="100%" my="1.5rem" mx="auto">
            <Header
              title="Edit Profile"
              text="Here you can edit public information about yourself."
              icon={<AiFillEdit />}
            />
          </Flex>
          {updated && (
            <Box className="edit-profile-success-msg">
              <Flex
                color="green.primary"
                fontSize="2.5rem"
                justifyContent="center"
                my="1.2rem"
              >
                <AiOutlineCheckCircle />
              </Flex>
              <Text textAlign="center" color="text.primary">
                Profile updated successfully.
              </Text>
            </Box>
          )}
          <InputContainer>
            <StandardFormInput
              htmlFor="email"
              errorField="Email"
              width="80%"
              error={form.email.error}
              name={form.email.name}
              value={form.email.value}
              type={form.email.type}
              updateField={updateField}
              label="Email"
            />
          </InputContainer>
          <InputContainer>
            <StandardFormInput
              htmlFor="phoneNumber"
              errorField="Phone number"
              width="80%"
              error={form.phoneNumber.error}
              name={form.phoneNumber.name}
              value={form.phoneNumber.value}
              type={form.phoneNumber.type}
              updateField={updateField}
              label="Phone"
            />
          </InputContainer>
          <InputContainer>
            <StandardFormInput
              htmlFor="city"
              errorField="City"
              width="80%"
              error={form.city.error}
              name={form.city.name}
              value={form.city.value}
              type={form.city.type}
              updateField={updateField}
              label="City"
            />
          </InputContainer>
          <InputContainer>
            <FormDropdown
              updateField={updateField}
              name={form.country.name}
              label="Country"
              value={form.country.value}
              data={countries}
            />
          </InputContainer>
          <InputContainer>
            <FormDropdown
              updateField={updateField}
              name={form.state.name}
              label="State"
              value={form.state.value}
              data={states}
            />
          </InputContainer>
          <Box
            bg="#fff"
            borderRadius="8px"
            boxShadow="rgba(149, 157, 165, 0.2) 0px 8px 24px"
            p="1.5rem"
            my="1.5rem"
            display="flex"
            justifyContent="center"
          >
            <Button type="submit" colorScheme="teal" width="80%">
              Edit
            </Button>
          </Box>
        </form>
      </Box>
    </Box>
  );
};

export default EditProfile;
