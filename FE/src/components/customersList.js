import React from 'react';

import style from './customersList.module.css';

const CustomerList = (props) => {
    return (
        <ul className={style.customersList}>
            {props.customers.map((customer) => (
                <li className={style.customer} key={customer.id}>
                    <h2>{customer.name}</h2>
                    <h3>{customer.phone}</h3>
                </li>
            ))}
        </ul>
    );
};

export default CustomerList;
