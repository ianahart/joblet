import { ReactNode } from 'react';

export interface ILoginResponse {
  refreshToken: string;
  token: string;
  userDto: IUser;
}

export interface ISyncSavedJobResponse {
  isSaved: boolean;
  savedJobId: number;
}

export interface IApplication {
  createdAt: ReactNode;
  employerId: number;
  id: number;
  jobCompany: string;
  jobId: number;
  jobPosition: string;
  profileId: number;
  userId: number;
}

export interface IReview {
  companyName: string;
  firstName: string;
  id: number;
  lastName: string;
  rating: number;
  text: string;
}

export interface IRetrieveReviewsResponse {
  page: number;
  reviewDtos: IReview[];
  totalPages: number;
}

export interface IApplicationSingleView {
  city: string;
  country: string;
  email: string;
  firstName: string;
  lastName: string;
  phoneNumber: string;
  resume: string;
  state: string;
}

export interface IRetrieveApplicationResponse {
  applicationDetailsDto: IApplicationSingleView;
}

export interface IRetrieveApplicationResponse {
  page: number;
  totalPages: number;
  applications: IApplication[];
}

export interface ISavedJob {
  availability: string;
  id: number;
  jobId: number;
  multipleCandidates: boolean;
  position: string;
  urgentlyHiring: boolean;
  userId: number;
  location: string;
  companyName: string;
}

export interface IGetSavedJobsResponse {
  jobs: ISavedJob[];
  page: number;
  size: number;
  totalPages: number;
}

export interface IEmployerJobMin {
  availability: string;
  body: string;
  multipleCandidates: boolean;
  perHour: number;
  position: string;
  urgentlyHiring: boolean;
  employerId: number;
}

export interface ICreateEmployerResponse {
  companyName: string;
  createdAt: Date;
  email: string;
  firstName: string;
  id: number;
  lastName: string;
  location: string;
  numOfEmployees: string;
  locationQuestionId: number;
}

export interface IEmployerJob {
  id: number;
  position: string;
  perHour: number;
  availability: string;
  urgentlyHiring: boolean;
  multipleCandidates: boolean;
  employerId: number;
  createdAt: Date;
  companyName: string;
  body: string;
  location: string;
  readableDate: string;
  firstName: string;
  lastName: string;
  email: string;
}

export interface IJob extends IEmployerJob {}

export interface IEmployerFullJob extends IEmployerJob {
  email: string;
  firstName: string;
  lastName: string;
  numOfEmployees: string;
}

export interface IGetEmployerJobResponse {
  page: number;
  size: number;
  totalPages: number;
  jobs: IEmployerJob[];
}

export interface IGetEmployerResponse {
  companyName: string;
  email: string;
  firstName: string;
  lastName: string;
  location: string;
  numOfEmployees: string;
  locationQuestionId: number;
}

export interface IAvailability {
  name: string;
  id: number;
  show: boolean;
}

export interface ICreateAccountForm {
  firstName: { name: string; value: string; error: string; type: string };
  lastName: { name: string; value: string; error: string; type: string };
  email: { name: string; value: string; error: string; type: string };
  password: { name: string; value: string; error: string; type: string };
}

export interface ICreateEmployerForm {
  companyName: { name: string; value: string; error: string; type: string };
  numOfEmployees: { name: string; value: string; error: string; type: string };
  firstName: { name: string; value: string; error: string; type: string };
  lastName: { name: string; value: string; error: string; type: string };
  email: { name: string; value: string; error: string; type: string };
  location: { name: string; value: string; error: string; type: string };
}

export interface IJobForm {
  position: { name: string; value: string; error: string; type: string };
  perHour: { name: string; value: string; error: string; type: string };
  employerId: { name: string; value: string; error: string; type: string };
  availability: { name: string; value: string; error: string; type: string };
  urgentlyHiring: { name: string; value: boolean; error: string; type: string };
  multipleCandidates: { name: string; value: boolean; error: string; type: string };
  body: { name: string; value: string; error: string; type: string };
}

export interface ILoginForm {
  email: { name: string; value: string; error: string; type: string };
  password: { name: string; value: string; error: string; type: string };
}

export interface IPasswordResetForm {
  oldPassword: { name: string; value: string; error: string; type: string };
  newPassword: { name: string; value: string; error: string; type: string };
  confirmPassword: { name: string; value: string; error: string; type: string };
}

export interface IEditProfileForm {
  email: { name: string; value: string; error: string; type: string };
  phoneNumber: { name: string; value: string; error: string; type: string };
  city: { name: string; value: string; error: string; type: string };
  state: { name: string; value: string; error: string; type: string };
  country: { name: string; value: string; error: string; type: string };
  resume: { name: string; value: string; error: string; type: string };
}

export interface IDropdownData {
  name: string;
  id: number;
  subTitle?: string;
  question?: string;
}

export interface IProfile {
  city: string;
  country: string;
  email: string;
  phoneNumber: string;
  resume: string;
  state: string;
  id: number;
  fileName: string;
}

export interface IUser {
  id: number;
  profileId: number;
  firstName: string;
  lastName: string;
  email: string;
  role: string;
  abbreviation: string;
  employerId: number;
}

export interface ITokens {
  refreshToken: string;
  token: string;
}

export interface ILocation {
  name: string;
  id: number;
  subTitle: string;
  question: string;
}

export interface IUserContext {
  user: IUser;
  tokens: ITokens;
  logout: () => void;
  setUser: (user: IUser) => void;
  setTokens: (tokens: ITokens) => void;
  stowTokens: (tokens: ITokens) => void;
  updateUser: (user: IUser) => void;
}
