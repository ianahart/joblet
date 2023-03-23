import EmployerForm from '../components/Job/EmployerForm';
const CreateEmployer = () => {
  return (
    <EmployerForm formType="create" title="Become an Employer" endpoint={`/employers/`} />
  );
};

export default CreateEmployer;
