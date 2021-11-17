import React from 'react';
import { Field, reduxForm } from 'redux-form';

import style from './FilteringForm.module.css'

const FilteringForm = props => {
    const { handleSubmit, pristine, submitting, invalid } = props;

    const countriesList = [
        { name: 'Cameroon', countryCode: '+237', regex: '\(237\)\ ?[2368]\d{7,8}$' },
        { name: 'Ethiopia', countryCode: '+251', regex: '\(251\)\ ?[1-59]\d{8}$' },
        { name: 'Morocco', countryCode: '+212', regex: '\(212\)\ ?[5-9]\d{8}$' },
        { name: 'Mozambique', countryCode: '+258', regex: '\(258\)\ ?[28]\d{7,8}$' },
        { name: 'Uganda', countryCode: '+256', regex: '\(256\)\ ?\d{9}$' },
    ];



    return (
        <form onSubmit={handleSubmit}>
            <div className={style.form}>
                <div className='form-control'>
                    <label className={style.label} htmlFor='country' >Country</label>
                    <Field
                        className={style.field}
                        name="country"
                        component="select"
                        required="true"
                    >
                        <option />
                        {countriesList.map(country =>
                            <option value={country.name}>{country.name}</option>
                        )}
                    </Field>
                </div>
                <div className='form-control'>
                    <label className={style.label} htmlFor='state'>State</label>
                    <Field
                        className={style.field}
                        name="state"
                        component="select"
                    >
                        <option />
                        <option value="valid">Valid</option>
                        <option value="not_valid">Not Valid</option>
                    </Field>
                </div>
                <div className='form-actions'>
                    <button className={style.submitButton} type="submit" disabled={pristine || submitting || invalid}>Submit</button>
                </div>
            </div>
        </form>
    );
};

export default reduxForm({ form: 'filtering_form' })(FilteringForm);
