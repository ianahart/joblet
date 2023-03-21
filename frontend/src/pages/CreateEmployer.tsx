import {
  Box,
  Button,
  Flex,
  Heading,
  Image,
  Radio,
  RadioGroup,
  Stack,
} from '@chakra-ui/react';
import {
  ICreateEmployerForm,
  ICreateEmployerResponse,
  IDropdownData,
  IGetEmployerResponse,
  IUserContext,
} from '../interfaces';
import { createEmployerState, locationState, numOfEmployees } from '../data';
import { useContext, useEffect, useState } from 'react';
import createEmployerImg from '../images/create-employer.jpg';
import StandardFormInput from '../components/Form/StandardFormInput';
import InputContainer from '../components/Form/InputContainer';
import FormDropdown from '../components/Form/FormDropdown';
import { AxiosError } from 'axios';
import { http } from '../helpers/utils';
import { useNavigate } from 'react-router-dom';
import { UserContext } from '../context/user';
import EmployerForm from '../components/Job/EmployerForm';
const CreateEmployer = () => {
  const navigate = useNavigate();
  const { user, setUser } = useContext(UserContext) as IUserContext;
  return (
    <EmployerForm formType="create" title="Become an Employer" endpoint={`/employers/`} />
  );
};

export default CreateEmployer;
