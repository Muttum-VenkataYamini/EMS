import React from 'react'
import { useState, useEffect } from 'react';
import { deleteEmployee, listEmployees } from '../services/EmployeeService';
import { Button } from 'bootstrap';
import { useNavigate } from 'react-router-dom';

const ListEmployeeComponent = () => {
    const [employees, setEmployees] = useState([]);
    const navigator = useNavigate();

    useEffect(
        () => {
            getAllEmployees();
            
        }, []);

        function getAllEmployees(){
            listEmployees().then((response) => {
                setEmployees(response.data);
            }).catch((error) => {
                console.log(error);
            })

        }

        function addNewEmployee() {
            navigator('/add-employee')

        }

        const updateEmployee = (id) => {
            navigator(`/update-employee/${id}`);
        }

        const removeEmployee = (id) => {
            deleteEmployee(id).then((response)=>{
                getAllEmployees();
            }).catch((error)=>{
                console.log(error);
            })
        }
      

    return (
        <div className='container'>
            <h2 className='text-center'>List of Employees</h2>
            <button className = 'btn btn-dark' onClick = {addNewEmployee}>Add Employee</button>
            <table className='table table-striped table-bordered'>
                <thead>
                    <tr>
                        <th>Employee ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email ID</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        employees.map((employee) =>
                            <tr key={employee.id}>
                                <td>{employee.id}</td>
                                <td>{employee.firstName}</td>
                                <td>{employee.lastName}</td>
                                <td>{employee.email}</td>
                                <td>
                                    <button className='btn btn-info' onClick={()=>{updateEmployee(employee.id)}} >Update</button>
                                    <button className='btn btn-danger ms-2'onClick={()=>{removeEmployee(employee.id)}} >Delete</button>
                                </td>
                            </tr>

                        )
                    }
                    <tr>

                    </tr>
                </tbody>
            </table>
        </div>


    )
}

export default ListEmployeeComponent