const mVar =125;
const lmVar = 35;
const margin = {top: lmVar, right: lmVar, bottom: mVar, left: lmVar};
const width = window.innerWidth;
const height = window.innerHeight - margin.top - margin.bottom ;

const svg = d3.select("#visualization")
    .append("svg")
    .attr("width", width  + margin.left + margin.right)
    .attr("height", height + margin.top + margin.bottom)
    .append("g")
    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

const x = d3.scaleBand().range([0, width]);

const y = d3.scaleLinear().range([height, 0]);

d3.json('http://localhost:8080/averages').then((data) => {
    console.log(data)
    data.forEach((d) => {
        d.average = +d.average;
    })

    x.domain(data.map((d) => {
        return d.state
    })).padding(0.1);

    y.domain([0, d3.max(data, (d) => {
        return d.average;
    })]);

    svg.selectAll(".bar")
        .data(data)
        .enter().append("rect")
        .attr("class", "bar")
        .attr("x", (d) => {
            return x(d.state);
        })
        .attr("width", x.bandwidth())
        .attr("y", (d) => {
            return y(d.average)
        })
        .attr("height", (d) => {
            return height - y(d.average)
        }).style("fill", "#006B38FF")
        .style("stroke", "#101820FF")

    svg.append("g")
        .attr("transform", `translate(0, ${height})`)
        .call(d3.axisBottom(x)).selectAll("text")
        .style("text-anchor", "end")
        .attr("dx", "-.9em")
        .attr("dy", ".25em")
        .attr("transform", "rotate(-65)");

    svg.append("g")
        .call(d3.axisLeft(y));

}).catch((error) => {
    console.error(error);
});