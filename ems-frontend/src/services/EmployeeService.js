import axios from 'axios'

const GET_ALL_EMPLOYEES_API_URL = 'http://localhost:8080/api/employees/getallemployees';
const CREATE_EMPLOYEE_API_URL = 'http://localhost:8080/api/employees/addemployee';
const UPDATE_EMPLOYEE_API_URL = 'http://localhost:8080/api/employees';
const GET_EMPLOYEE_BY_ID_API_URL = 'http://localhost:8080/api/employees';
const DELETE_EMPLOYEE_API_URL = 'http://localhost:8080/api/employees/delete';



export const listEmployees = () => axios.get(GET_ALL_EMPLOYEES_API_URL);

export const createEmployee = (employee)=>  axios.post(CREATE_EMPLOYEE_API_URL, employee);

export const getEmployeeById =(employeeId)=> axios.get(GET_EMPLOYEE_BY_ID_API_URL + '/' + employeeId)

export const updateEmployee = (employeeId,employee)=> axios.put(UPDATE_EMPLOYEE_API_URL + '/'+ employeeId, employee);

export const deleteEmployee = (employeeId)=> axios.delete(DELETE_EMPLOYEE_API_URL + '/' + employeeId);



