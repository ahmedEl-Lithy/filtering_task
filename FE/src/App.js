import React, { useState, useEffect, useCallback } from 'react';

import './App.css';
import CustomerList from './components/customersList';
import FilteringForm from './components/FilteringForm';

function App() {
  const [customers, setCustomers] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  const fetchCustomersHandler = useCallback(async () => {
    setIsLoading(true);
    setError(null);
    try {
      const response = await fetch('/api/customer');
      const body = await response.json();
      setCustomers(body);
    } catch (error) {
      setError(error.message);
    }
    setIsLoading(false);
  }, []);

  useEffect(() => {
    fetchCustomersHandler();
  }, [fetchCustomersHandler]);

  const filterCustomers = async (country, state) => {
    setIsLoading(true);
    setError(null);

    try {
      const response = await fetch('/api/filter_customers', {
        method: 'post',
        headers: { 'Accept': 'application/json', 'Content-Type': 'application/json' },
        body: JSON.stringify({
          'country': country,
          'state': state
        })
      });
      const body = await response.json();
      console.log(body);
      setCustomers(body);
    } catch (error) {
      setError(error.message);
    }
    setIsLoading(false);
  };

  let content = <p>Found no customers.</p>;

  if (customers.length > 0) {
    content = <CustomerList customers={customers} />;
  }

  if (error) {
    content = <p>{error}</p>;
  }

  if (isLoading) {
    content = <p>Loading...</p>;
  }

  const showResults = (values) => {
    const selectedCountry = values?.country;
    const selectedState = values?.state;

    filterCustomers(selectedCountry, selectedState);
  };

  return (
    <>
      <section>
        <h2>Customers</h2>
        <FilteringForm onSubmit={showResults} />
      </section>
      <section>{content}</section>
    </>
  );

}
export default App;