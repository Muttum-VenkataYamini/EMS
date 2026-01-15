import React, { useEffect } from 'react'
import { useState } from 'react';
import { createEmployee, getEmployeeById , updateEmployee } from '../services/EmployeeService';
import { useNavigate , useParams} from 'react-router-dom';

const EmployeeComponent = () => {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const navigate = useNavigate();
    const { id } = useParams();


    useEffect(()=>{
        if(id){
            getEmployeeById(id).then((response)=>{
                setFirstName(response.data.firstName);
                setLastName(response.data.lastName);
                setEmail(response.data.email);
            }).catch((error)=>{
                console.log(error);
            })
        }
    },[id])


    const [errors, setErrors] = useState({
        firstName: '',
        lastName: '',
        email: ''
    })

    function validateForm() {
        let valid = true;
        const errorsCopy = { ...errors }

        if (firstName.trim()) {
            errorsCopy.firstName = '';
        }
        else {
            errorsCopy.firstName = 'First Name is required';
            valid = false;
        }
        if (lastName.trim()) {
            errorsCopy.lastName = '';
        }
        else {
            errorsCopy.lastName = 'Last Name is required';
            valid = false;
        }
         if (email.trim()) {
            errorsCopy.email = '';
        }
        else{
            errorsCopy.email = 'Email is required';
            valid = false;
        }
        setErrors(errorsCopy);
        return valid;
    }

    function saveOrUpdateEmployee(e) {
        e.preventDefault();
        const employee = { firstName, lastName, email };
        console.log(employee);

        if(validateForm()){

            if(id){
                //Update existing employee
                    updateEmployee(id, employee).then((response) => {
                    console.log(response.data);
                    navigate('/employees');
                }).catch((error) => {
                    console.error('Update employee failed:', error.response?.data || error.message);
                });
            } else {
                //Create new employeem
                    createEmployee(employee).then((response) => {
                    console.log(response.data);
                    navigate('/employees');
                }).catch((error) => {
                    console.error('Error creating employee:', error.response?.data || error.message);
                });         
            }
        

        }   
    }

    function pageTitle(){
        if(id){
            return <h2 className='text-center'> Update Employee </h2>
        } else {
            return <h2 className='text-center'> Add Employee </h2>
        }

    }

    return (
        <div className='container'>
            <br />
            <br />
            <div className='row'>
                <div className='card col-md-6 offset-md-3 offset-md-3 '>
                    {pageTitle()}
                    <div className='card-body'>
                        <form onSubmit={saveOrUpdateEmployee}>
                            <div className='form-group mb-2'>
                                <label className='form-label'> First Name :</label>
                                <input type='text' placeholder='Enter First Name' name='firstName' className={`form-control ${errors.firstName ? 'is-invalid' : ''}`}
                                    value={firstName} onChange={(e) => setFirstName(e.target.value)}></input>

                                    {errors.firstName && <div className='invalid-feedback'> {errors.firstName} </div>}

                                <label className='form-label'> Last Name :</label>
                                <input type='text' placeholder='Enter Last Name' name='lastName' className={`form-control ${errors.lastName ? 'is-invalid' : ''}`}
                                    value={lastName} onChange={(e) => setLastName(e.target.value)}></input>

                                {errors.lastName && <div className='invalid-feedback'> {errors.lastName} </div>}

                                <label className='form-label'> Email ID :</label>
                                <input type='text' placeholder='Enter Email ID' name='email' className={`form-control ${errors.email ? 'is-invalid' : ''}`}
                                    value={email} onChange={(e) => { setEmail(e.target.value) }}></input>

                                {errors.email && <div className='invalid-feedback'> {errors.email} </div>}

                            </div>
                            <button type='submit' className='btn btn-success mt-2'>Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    )
}
export default EmployeeComponent;