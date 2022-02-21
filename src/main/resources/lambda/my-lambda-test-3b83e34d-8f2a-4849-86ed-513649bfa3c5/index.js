exports.handler = async (event) => {
    // TODO implement
    
    console.log("Entrou aqui: ", event)
    const response = {
        statusCode: 200,
        body: JSON.stringify('Hello from Lambda!'),
    };
    return response;
};
