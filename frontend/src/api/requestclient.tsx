import axios, { AxiosInstance, AxiosResponse, AxiosError } from 'axios';

class RequestClient {
    private readonly httpClient: AxiosInstance;

    constructor(private readonly url: string) {
        this.httpClient = axios.create({ baseURL: url });
    }

    public setAuthenticationToken(token: string) {
        this.httpClient.defaults.headers.common['Authorization'] = `Bearer ${token}`;
    }

    public async post(path: string, data: any) {
        try {
            const response: AxiosResponse = await this.httpClient.post(path, data);
            return response.data;
        } catch (error) {
            this.handleError(error);
            throw error;
        }
    }

    public async put(path: string, data: any) {
        try {
            const response: AxiosResponse = await this.httpClient.put(path, data);
            return response.data;
        } catch (error) {
            this.handleError(error);
            throw error;
        }
    }

    public async delete(path: string) {
        try {
            const response: AxiosResponse = await this.httpClient.delete(path);
            return response.data;
        } catch (error) {
            this.handleError(error);
            throw error;
        }
    }

    public async get(path: string) {
        try {
            const response: AxiosResponse = await this.httpClient.get(path);
            return response.data;
        } catch (error) {
            this.handleError(error);
        }
    }

    private handleError(error: any) {
        if (error.response) {
            // Request was made and server responded with a status code outside the range of 2xx
            console.error('Request failed with response:', error.response.data);
        } else if (error.request) {
            // Request was made but no response was received
            console.error('No response received:', error.request);
        } else {
            // Something happened in setting up the request that triggered an Error
            console.error('Error setting up the request:', error.message);
        }
        throw error.response.data
    }
}

const requestClient = new RequestClient('http://localhost:8080/')

export { requestClient }
